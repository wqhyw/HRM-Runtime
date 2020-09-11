package hrm.engine

/**
 *  @author wqhyw
 *  @date 2020-08-21 18:14:24
 */
class Program(builder: Program.() -> Unit) {
    private val _program: MutableList<Instruction<*>> = mutableListOf()

    init {
        builder()
    }

    val program get() = ArrayList(_program)

    fun INBOX() = _program.add(InBox())
    fun OUTBOX() = _program.add(OutBox())
    fun BUMPUP(p: Int) = _program.add(BumpUp(p))
    fun BUMPDN(p: Int) = _program.add(BumpDown(p))
    fun COPYFROM(p: Int) = _program.add(CopyFrom(p))
    fun COPYTO(p: Int) = _program.add(CopyTo(p))
    fun ADD(p: Int) = _program.add(Add(p))
    fun SUB(p: Int) = _program.add(Sub(p))
    fun JUMP(p: String) = _program.add(Jump(p))
    fun JUMPZ(p: String) = _program.add(JumpZ(p))
    fun JUMPN(p: String) = _program.add(JumpN(p))
    fun LABEL(p: String) = _program.add(Label(p))
    fun COPYFROM_D(p: Int) = _program.add(CopyFromD(p))
    fun COPYTO_D(p: Int) = _program.add(CopyToD(p))
    fun ADD_D(p: Int) = _program.add(AddD(p))
    fun SUB_D(p: Int) = _program.add(SubD(p))
    fun BUMPUP_D(p: Int) = _program.add(BumpUpD(p))
    fun BUMPDN_D(p: Int) = _program.add(BumpDownD(p))

    override fun toString() = _program.joinToString(separator = "\n") { it.name }
}