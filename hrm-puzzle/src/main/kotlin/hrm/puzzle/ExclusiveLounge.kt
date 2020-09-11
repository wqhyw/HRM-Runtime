package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

object ExclusiveLounge : Puzzle<Int> {
    override val numbering = 17

    override val evaluation = Evaluation(12, 28)

    override fun rule(inbox: List<Int>) = inbox.chunked(2) { if (it[0] * it[1] > 0) 0 else 1 }

    override fun sampleInbox() = Puzzle.sampleInt(8) { it != 0 }

    override fun sampleTiles(): Map<Int, Int> = mapOf(4 to 0, 5 to 1)

    override fun sampleSolution() =
        Program {
            JUMP("d")
            LABEL("a")
            INBOX()
            JUMPN("e")
            LABEL("b")
            COPYFROM(5)
            LABEL("c")
            OUTBOX()
            LABEL("d")
            INBOX()
            JUMPN("a")
            INBOX()
            JUMPN("b")
            LABEL("e")
            COPYFROM(4)
            JUMP("c")
        }
}
