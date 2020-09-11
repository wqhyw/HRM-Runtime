package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program
import hrm.puzzle.Puzzle.Companion.chunkedBy0

/**
 *  @author wqhyw
 *  @date 2020-08-28 18:17:13
 */
object StringReverse : Puzzle<Any?> {
    override val numbering = 31

    override val evaluation = Evaluation(11, 122)

    override fun rule(inbox: List<Any?>) = inbox.chunkedBy0 { it.asReversed() }.flatten()

    override fun sampleInbox() = listOf("T", "E", "A", 0, "M", "O", "R", "E", 0, "B", "U", "G", 0)

    override fun sampleTiles() = mapOf(14 to 0)

    override fun sampleSolution() =
        Program {
            LABEL("a")
            LABEL("b")
            BUMPUP(14)
            INBOX()
            JUMPZ("c")
            COPYTO_D(14)
            JUMP("b")
            LABEL("c")
            LABEL("d")
            BUMPDN(14)
            JUMPZ("a")
            COPYFROM_D(14)
            OUTBOX()
            JUMP("d")
        }
}