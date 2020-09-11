package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-20 11:40:41
 */
object RainySummer : Puzzle<Int> {
    override val numbering = 6

    override val evaluation = Evaluation(6, 24)

    override fun rule(inbox: List<Int>) = inbox.chunked(2) { it.sum() }

    override fun sampleInbox() = Puzzle.sampleInt(8)

    override fun sampleSolution() =
        Program {
            LABEL("a")
            INBOX()
            COPYTO(0)
            INBOX()
            ADD(0)
            OUTBOX()
            JUMP("a")
        }
}