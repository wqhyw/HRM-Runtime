package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-28 11:37:09
 */
object StringStorageFloor : Puzzle<Any?> {
    override val numbering = 30

    override val evaluation = Evaluation(7, 203)

    override fun rule(inbox: List<Any?>) =
        listOf("T", "H", "E", "T", "A", "R", "G", "E", "T", "X", "I", "S", "A", "W", "A", "K", "E", "X", "X", "X", "T", "A", "K", "E", "T", "H", "I", "S", "X")

    override fun sampleInbox() = listOf(4, 15, 7, 0, 22, 17, 11, 20, 2, 13, 4, 17, 22)

    override fun sampleTiles() =
        listOf("G", "E", "T", 0, "T", "H", 0, "T", "A", "R", 0, "A", "W", "A", "K", "E", 0, "I", "S", 0, "X", "X", "X", 0)
            .withIndex()
            .associate { it.index to it.value }

    override fun sampleSolution() =
        Program {
            LABEL("a")
            INBOX()
            COPYTO(24)
            LABEL("b")
            COPYFROM_D(24)
            JUMPZ("a")
            OUTBOX()
            BUMPUP(24)
            JUMP("b")
        }
}