package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-19 15:45:17
 */
object MailRoom : Puzzle<Int> {
    override val numbering = 1

    override val evaluation = Evaluation(6, 6)

    override fun rule(inbox: List<Int>) = inbox.map { it }

    override fun sampleInbox() = Puzzle.sampleInt(3)

    override fun sampleSolution() =
        Program {
            INBOX()
            OUTBOX()
            INBOX()
            OUTBOX()
            INBOX()
            OUTBOX()
        }
}