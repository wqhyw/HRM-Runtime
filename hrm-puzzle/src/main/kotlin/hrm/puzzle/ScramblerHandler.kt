package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-19 16:50:13
 */
object ScramblerHandler : Puzzle<Int> {
    override val numbering = 4

    override val evaluation = Evaluation(7, 21)

    override fun rule(inbox: List<Int>) = inbox.chunked(2) { it.reversed() }.flatten()

    override fun sampleInbox() = Puzzle.sampleInt(6)

    override fun sampleSolution() =
        Program {
            LABEL("a")
            INBOX()
            COPYTO(0)
            INBOX()
            OUTBOX()
            COPYFROM(0)
            OUTBOX()
            JUMP("a")
        }
}