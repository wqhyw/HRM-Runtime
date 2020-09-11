package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-21 17:58:12
 */
object SubHallway : Puzzle<Int> {
    override val numbering = 11

    override val evaluation = Evaluation(10, 40)

    override fun rule(inbox: List<Int>) =
        inbox
            .chunked(2) { listOf(it[1] - it[0], it[0] - it[1]) }
            .flatten()

    override fun sampleInbox() = Puzzle.sampleInt(8)

    override fun sampleSolution() =
        Program {
            LABEL("a")
            INBOX()
            COPYTO(0)
            INBOX()
            COPYTO(1)
            SUB(0)
            OUTBOX()
            COPYFROM(0)
            SUB(1)
            OUTBOX()
            JUMP("a")
        }
}