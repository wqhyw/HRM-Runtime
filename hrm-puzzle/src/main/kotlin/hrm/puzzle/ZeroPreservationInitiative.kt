package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-21 16:45:41
 */
object ZeroPreservationInitiative : Puzzle<Any> {
    override val numbering = 9

    override val evaluation = Evaluation(5, 25)

    override fun rule(inbox: List<Any>) = inbox.filter { it == 0 }

    override fun sampleInbox() = listOf(2, 0, 1, "B", 0, 0, 6, 0)

    override fun sampleSolution() =
        Program {
            JUMP("c")
            LABEL("a")
            OUTBOX()
            LABEL("b")
            LABEL("c")
            INBOX()
            JUMPZ("a")
            JUMP("b")
        }
}