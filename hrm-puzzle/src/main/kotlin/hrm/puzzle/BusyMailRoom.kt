package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-19 15:46:57
 */
object BusyMailRoom : Puzzle<Char> {
    override val numbering = 2

    override val evaluation = Evaluation(3, 25)

    override fun rule(inbox: List<Char>) = inbox.map { it }

    override fun sampleInbox() = Puzzle.sampleChar(10)

    override fun sampleSolution() =
        Program {
            LABEL("a")
            INBOX()
            OUTBOX()
            JUMP("a")
        }
}