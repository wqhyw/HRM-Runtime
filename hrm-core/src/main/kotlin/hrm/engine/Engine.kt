package hrm.engine

import kotlin.reflect.full.primaryConstructor

/**
 *  @author wqhyw
 *  @date 2020-08-07 18:13:29
 */

private class ProgramCounter<E>(val pc: Int = 0, val content: E? = null) {
    fun move(
        newContent: E? = content,
        newPc: Int = pc + 1,
        sideEffect: () -> Unit = {},
    ): ProgramCounter<E> {
        sideEffect.invoke()
        return ProgramCounter(newPc, newContent)
    }
}

data class Evaluation(
    val size: Int,
    val speed: Int,
)

class Engine<E>(
    program: Program,
    rawInput: List<E>,
    tiles: Map<Int, E?> = mapOf(),
) {
    private val _inbox = ArrayList(rawInput)

    private val inboxIterator = _inbox.listIterator()

    private val _floor = tiles.toMap(mutableMapOf())

    private val _instructions = mutableListOf<Instruction<*>>()

    private val labels = mutableMapOf<String, Int>()

    private val _outbox = mutableListOf<E>()

    private var _pc = ProgramCounter<E>()

    private var speed = 0

    init {
        program.program
            .forEachIndexed { i, it ->
                if (it is Label) {
                    labels[it.param] = i
                }

                _instructions.add(it)
            }
    }

    //region instruction executors
    private fun inbox() = _pc moveWith inboxIterator.next()

    private fun outbox() = _pc.move { _outbox.add(_pc.content ?: throw PcIsNullException()) }

    private fun label() = _pc.move()

    private fun bumpUp(ins: Instruction<Int>) =
        _pc moveWith _floor.compute(ins.param) { _, _ -> ins.calWithFloor { it + 1 } }

    private fun bumpDown(ins: Instruction<Int>) =
        _pc moveWith _floor.compute(ins.param) { _, _ -> ins.calWithFloor { it - 1 } }

    private fun copyFrom(ins: Instruction<Int>) = _pc moveWith _floor[ins.param]

    private fun add(ins: Instruction<Int>) =
        _pc moveWith ins.calWithFloor { tile -> _pc.calWithContent { ctx -> ctx + tile } }

    private fun sub(ins: Instruction<Int>) = _pc subFloor ins

    private fun copyTo(ins: Instruction<Int>) =
        _pc.move { _floor[ins.param] = _pc.content ?: throw PcIsNullException() }

    private fun jump(ins: Instruction<String>) =
        _pc jump (labels[ins.param] ?: throw NoSuchLabelException()) + 1

    private fun jumpN(ins: Instruction<String>) =
        if ((_pc.content as? Int ?: 1) < 0) {
            jump(Jump(ins.param))
        } else {
            _pc.move()
        }

    private fun jumpZ(ins: Instruction<String>) =
        if ((_pc.content as? Int ?: 1) == 0) {
            jump(Jump(ins.param))
        } else {
            _pc.move()
        }

    private fun ProgramCounter<E>.calWithContent(computation: (Int) -> Int) =
        computation.invoke(content as? Int ?: throw PcNotCalculableException())

    private infix fun ProgramCounter<E>.jump(newPc: Int): ProgramCounter<E> = move(content, newPc)

    private infix fun ProgramCounter<E>.moveWith(newContent: E?): ProgramCounter<E> = move(newContent)

    private infix fun ProgramCounter<E>.subFloor(ins: Instruction<Int>): ProgramCounter<E> {
        val currentTile = _floor[ins.param] ?: throw PcAndTileCannotCompareException()

        return if (content is Int && currentTile is Int) {
            _pc moveWith ins.calWithFloor { tile -> calWithContent { inner -> inner - tile } }
        } else {
            if (content is Comparable<*>) {
                _pc moveWith compare(content, currentTile)
            } else {
                throw PcAndTileCannotCompareException()
            }
        }
    }

    private val Instruction<Int>.deReference
        get() = this::class.primaryConstructor
            ?.call(_floor[param as? Int ?: throw FloorCannotDereferenceException()])
            ?: throw FloorCannotDereferenceException()

    @Suppress("UNCHECKED_CAST")
    private fun compare(a: E, b: E) : E {
        if (a is Comparable<*> && b is Comparable<*>) {
            return (a as Comparable<E>).compareTo(b) as E
        } else {
            throw PcAndTileCannotCompareException()
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun Instruction<*>.calWithFloor(computation: (Int) -> Int) =
        computation.invoke(_floor[param] as? Int ?: throw FloorNotCalculableException()) as E?

    private fun exec(): Boolean =
        if (hasNext) {
             val instruction = currentInstruction

            _pc = when (instruction) {
                is InBox -> inbox()
                is OutBox -> outbox()
                is Label -> label()
                is BumpUp -> bumpUp(instruction)
                is BumpDown -> bumpDown(instruction)
                is CopyFrom -> copyFrom(instruction)
                is CopyTo -> copyTo(instruction)
                is Add -> add(instruction)
                is Sub -> sub(instruction)
                is Jump -> jump(instruction)
                is JumpZ -> jumpZ(instruction)
                is JumpN -> jumpN(instruction)
                is CopyToD -> copyTo(instruction.deReference)
                is CopyFromD -> copyFrom(instruction.deReference)
                is AddD -> add(instruction.deReference)
                is SubD -> sub(instruction.deReference)
                is BumpUpD -> bumpUp(instruction.deReference)
                is BumpDownD -> bumpDown(instruction.deReference)
            }

            speed += instruction.speed

            true
        } else {
            false
        }
    //endregion

    //region public api
    val instructionSize by lazy { _instructions.filter { it !is Label }.size }

    val currentInstruction get() = _instructions[_pc.pc]

    val hasNext get() = _pc.pc < _instructions.size && (currentInstruction !is InBox || inboxIterator.hasNext())

    val evaluation get() = Evaluation(instructionSize, speed)

    val outbox get() = ArrayList(_outbox)

    fun run(threshold: Int = 1400) {
        var steps = 0

        while (steps < threshold && exec()) steps++

        if (steps >= threshold) throw StepsTooMuchException()
    }
    //endregion
}
