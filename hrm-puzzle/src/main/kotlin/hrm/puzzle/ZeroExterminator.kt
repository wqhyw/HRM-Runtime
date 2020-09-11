package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-20 11:48:57
 */
object ZeroExterminator : Puzzle<Any> {
    override val numbering = 7

    override val evaluation = Evaluation(4, 23)

    override fun rule(inbox: List<Any>) = inbox.filter { it != 0 }

    override fun sampleInbox() = listOf(8, 0, -4, "A", 0, 0, 9, 0)

    override fun sampleSolution() =
        Program {
            LABEL("a")
            LABEL("b")
            INBOX()
            JUMPZ("b")
            OUTBOX()
            JUMP("a")
        }
}