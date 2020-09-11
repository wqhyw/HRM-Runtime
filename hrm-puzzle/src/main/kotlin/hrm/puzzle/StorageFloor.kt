package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-27 15:48:10
 */
object StorageFloor : Puzzle<Any?> {
    override val numbering = 29

    override val evaluation = Evaluation(5, 25)

    override fun rule(inbox: List<Any?>) = listOf('L', 'E', 'E', 'Y', 'Y')

    override fun sampleInbox() = listOf(7, 3, 3, 8, 8)

    override fun sampleTiles() =
        listOf('N', 'K', 'A', 'E', 'R', 'D', 'O', 'L', 'Y', 'J')
            .withIndex()
            .associate { it.index to it.value }

    override fun sampleSolution() =
        Program {
            LABEL("a")
            INBOX()
            COPYTO(10)
            COPYFROM_D(10)
            OUTBOX()
            JUMP("a")
        }
}