package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-21 16:39:05
 */
object TriplerRoom : Puzzle<Int> {
    override val numbering = 8

    override val evaluation = Evaluation(6, 24)

    override fun rule(inbox: List<Int>) = inbox.map { it * 3 }

    override fun sampleInbox() = Puzzle.sampleInt(4)

    override fun sampleSolution() =
        Program {
            LABEL("a")
            INBOX()
            COPYTO(0)
            ADD(0)
            ADD(0)
            OUTBOX()
            JUMP("a")
        }
}