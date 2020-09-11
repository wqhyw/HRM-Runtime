package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-24 17:27:31
 */
object Countdown : Puzzle<Int> {
    override val numbering = 19

    override val evaluation = Evaluation(10, 82)

    override fun rule(inbox: List<Int>) = inbox.map { if (it > 0) it downTo 0 else it..0 }.flatten()

    override fun sampleInbox() = Puzzle.sampleInt(4)

    override fun sampleSolution() =
        Program {
            LABEL("a")
            INBOX()
            COPYTO(0)
            JUMP("c")
            LABEL("b")
            OUTBOX()
            BUMPUP(0)
            LABEL("c")
            JUMPN("b")
            LABEL("d")
            OUTBOX()
            BUMPDN(0)
            JUMPN("a")
            JUMP("d")
        }
}