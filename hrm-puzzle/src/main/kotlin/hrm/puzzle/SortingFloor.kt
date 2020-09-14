package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program
import hrm.puzzle.Puzzle.Companion.chunkedBy0

/**
 *  @author wqhyw
 *  @date 2020-09-14 14:48:12
 */
object SortingFloor : Puzzle<Any> {
    override val numbering = 41

    override val evaluation = Evaluation(20, 446)

    override fun rule(inbox: List<Any>) =
        inbox.chunkedBy0 { it.sortedBy { c -> c.toString() } }
            .flatten()

    override fun sampleInbox() =
        listOf(91, 21, 46, 0, "T", "H", "I", "N", "K", 0, 86, 85, 83, 37, 32, 51, 19, 62, 72, 59, 0, 66, 0)

    override fun sampleTiles() = mapOf(24 to 0)

    override fun sampleSolution() =
        Program {
            LABEL("a")
            LABEL("b")
            BUMPUP(24)
            INBOX()
            JUMPZ("d")
            COPYTO_D(24)
            JUMP("a")
            LABEL("c")
            COPYFROM_D(22)
            OUTBOX()
            COPYFROM_D(24)
            COPYTO_D(22)
            LABEL("d")
            BUMPDN(24)
            JUMPZ("b")
            COPYTO(23)
            LABEL("e")
            COPYTO(22)
            LABEL("f")
            BUMPDN(23)
            JUMPZ("c")
            COPYFROM_D(22)
            SUB_D(23)
            JUMPN("f")
            COPYFROM(23)
            JUMP("e")

        }
}