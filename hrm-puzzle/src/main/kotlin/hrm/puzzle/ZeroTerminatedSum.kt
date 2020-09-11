package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program
import hrm.puzzle.Puzzle.Companion.chunkedBy0

/**
 *  @author wqhyw
 *  @date 2020-08-25 12:17:06
 */
object ZeroTerminatedSum : Puzzle<Int> {
    override val numbering = 21

    override val evaluation = Evaluation(10, 72)

    override fun rule(inbox: List<Int>) = inbox.chunkedBy0 { it.sum() }

    override fun sampleInbox() = listOf(7, 7, 0, 2, -9, 8, 0, 0, 0, 2, -9, 1, 2, -8, 1, 0)

    override fun sampleTiles() = mapOf(5 to 0)

    override fun sampleSolution() =
        Program {
            JUMP("b")
            LABEL("a")
            COPYFROM(0)
            OUTBOX()
            LABEL("b")
            COPYFROM(5)
            LABEL("c")
            COPYTO(0)
            INBOX()
            JUMPZ("a")
            ADD(0)
            JUMP("c")
        }
}