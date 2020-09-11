package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-19 16:30:37
 */
object CopyFloor : Puzzle<Char> {
    override val numbering = 3

    override val evaluation = Evaluation(6, 6)

    override fun rule(inbox: List<Char>) = listOf('B', 'U', 'G')

    override fun sampleInbox() = listOf<Char>()

    override fun sampleTiles(): Map<Int, Char> = "UJXGBE".withIndex().associate { it.index to it.value }

    override fun sampleSolution() =
        Program {
            COPYFROM(4)
            OUTBOX()
            COPYFROM(0)
            OUTBOX()
            COPYFROM(3)
            OUTBOX()
        }
}