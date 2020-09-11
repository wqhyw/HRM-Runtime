package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-25 17:00:36
 */
object CumulativeCountdown : Puzzle<Int> {
    override val numbering = 25

    override val evaluation = Evaluation(12, 82)

    override fun rule(inbox: List<Int>) = inbox.map { (it downTo 0).sum() }

    override fun sampleInbox() = Puzzle.sampleInt(4) { it >= 0 }

    override fun sampleTiles() = mapOf(5 to 0)

    override fun sampleSolution() =
        Program {
            JUMP("b")
            LABEL("a")
            COPYFROM(0)
            OUTBOX()
            LABEL("b")
            INBOX()
            COPYTO(1)
            LABEL("c")
            COPYTO(0)
            BUMPDN(1)
            JUMPN("a")
            ADD(0)
            JUMP("c")
        }
}