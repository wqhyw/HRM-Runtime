package hrm.puzzle

import hrm.engine.Engine
import hrm.engine.EngineExecutionException
import hrm.engine.Evaluation
import hrm.engine.Program
import kotlin.random.Random

/**
 *  @author wqhyw
 *  @date 2020-08-19 15:26:10
 */
interface Puzzle<E> {
    /**
     * hrm.puzzle numbering
     */
    val numbering: Int

    /**
     * evaluation
     */
    val evaluation: Evaluation

    /**
     * trans input to output by hrm.puzzle rule
     */
    fun rule(inbox: List<E>): List<E>

    /**
     * sample inbox
     */
    fun sampleInbox(): List<E>

    /**
     * defaylt tiles
     */
    fun sampleTiles() = mapOf<Int, E>()

    /**
     * default solution
     */
    fun sampleSolution(): Program

    /**
     * try solve with Program
     *
     * @return pair of result (pair of succeed and actual output) and evaluation
     */
    fun trySolve(
        program: Program = sampleSolution()
    ): SolveResult<E> {
        val rawInput = sampleInbox()


        val engine = Engine(program, rawInput, sampleTiles())

        try {
            engine.run()
        } catch (e: EngineExecutionException) {
            e.printStackTrace()
            return SolveResult(
                rawInput,
                listOf(),
                false,
                Evaluation(engine.evaluation.size, -1)
            )
        }

        return SolveResult(
            rawInput,
            engine.outbox,
            engine.outbox == rule(rawInput),
            engine.evaluation
        )
    }

    companion object {
        val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

        fun sampleInt(
            size: Int,
            from: Int = -9,
            end: Int = 10,
            predicate: (Int) -> Boolean = { true }
        ) = List(size) {
            var rand = Random.nextInt(from, end)

            while (!predicate(rand)) rand = Random.nextInt(from, end)

            rand
        }

        fun sampleChar(size: Int) = List(size) { alphabet.random() }

        fun <E, R> List<E>.chunkedBy0(transformer: (List<E>) -> R): List<R> {
            val res = mutableListOf<R>()
            var tmp = mutableListOf<E>()
            for (i in this) {
                if (i == 0) {
                    res.add(transformer.invoke(tmp));
                    tmp = mutableListOf();
                } else {
                    tmp.add(i);
                }
            }

            return res
        }

        data class SolveResult<E>(
            val inbox: List<E>,
            val outbox: List<E>,
            val succeed: Boolean,
            val evaluation: Evaluation,
        )
    }
}