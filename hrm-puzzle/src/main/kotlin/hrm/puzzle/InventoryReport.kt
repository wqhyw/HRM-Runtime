package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

object InventoryReport : Puzzle<Any?> {
    override val numbering = 32

    override val evaluation = Evaluation(16, 393)

    override fun rule(inbox: List<Any?>) =
        inbox.map { sampleTiles().values.filter { v -> v == it }.count() }

    override fun sampleInbox() = listOf('X', 'A', 'C', 'B')

    override fun sampleTiles() =
        listOf('B', 'A', 'X', 'B', 'C', 'X', 'A', 'B', 'A', 'X', 'C', 'B', 'A', 'B', 0)
            .withIndex()
            .associate { it.index to it.value }

    override fun sampleSolution() =
        Program {
            JUMP("b")
            LABEL("a")
            COPYFROM(15)
            OUTBOX()
            LABEL("b")
            INBOX()
            COPYTO(16)
            COPYFROM(14)
            COPYTO(15)
            COPYTO(17)
            JUMP("e")
            LABEL("c")
            BUMPUP(15)
            LABEL("d")
            BUMPUP(17)
            LABEL("e")
            COPYFROM_D(17)
            JUMPZ("a")
            SUB(16)
            JUMPZ("c")
            JUMP("d")
        }
}