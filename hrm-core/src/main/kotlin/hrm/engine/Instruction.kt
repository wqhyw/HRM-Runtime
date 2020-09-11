package hrm.engine

/**
 *  @author wqhyw
 *  @date 2020-08-06 18:16:18
 */

/**
 * Mark interface which has no param
 */
private interface Instruction0

/**
 * Mark interface which has one param
 */
private interface Instruction1

sealed class Instruction<E> {
    open val param: E get() = throw UnsupportedOperationException("No Parameter Provided")
    open val speed = 1
}

class InBox : Instruction<Void>(), Instruction0

class OutBox : Instruction<Void>(), Instruction0

class BumpUp(override val param: Int) : Instruction<Int>(), Instruction1

class BumpDown(override val param: Int) : Instruction<Int>(), Instruction1

class CopyFrom(override val param: Int) : Instruction<Int>(), Instruction1

class CopyTo(override val param: Int) : Instruction<Int>(), Instruction1

class Add(override val param: Int) : Instruction<Int>(), Instruction1

/**
 * when operand is Integer, sub means minus
 * when operand is Object, sub means compareTo
 */
class Sub(override val param: Int) : Instruction<Int>(), Instruction1

class Jump(override val param: String) : Instruction<String>(), Instruction1

class JumpZ(override val param: String) : Instruction<String>(), Instruction1

class JumpN(override val param: String) : Instruction<String>(), Instruction1

/**
 * dereferencing
 */
class CopyToD(override val param: Int) : Instruction<Int>(), Instruction1

class CopyFromD(override val param: Int) : Instruction<Int>(), Instruction1

class AddD(override val param: Int) : Instruction<Int>(), Instruction1

class SubD(override val param: Int) : Instruction<Int>(), Instruction1

class BumpUpD(override val param: Int) : Instruction<Int>(), Instruction1

class BumpDownD(override val param: Int) : Instruction<Int>(), Instruction1

/**
 * Label for jump, Not name of floor
 */
class Label(override val param: String) : Instruction<String>(), Instruction1 {
    override val speed = 0
}

val <E> Instruction<E>.name: String
    get() {
        val name = this::class.simpleName?.toUpperCase() ?: ""

        return when (this) {
            is Instruction0 -> name
            is Instruction1 -> "$name\t$param"
            else -> throw UnsupportedOperationException("No Name Provided")
        }
    }