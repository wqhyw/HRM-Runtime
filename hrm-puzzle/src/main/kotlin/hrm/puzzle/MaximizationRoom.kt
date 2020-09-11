package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-24 10:49:00
 */

object MaximizationRoom : Puzzle<Int> {
    override val numbering = 14

    override val evaluation = Evaluation(10, 34)

    override fun rule(inbox: List<Int>) = inbox.chunked(2) { it.maxOrNull() }.filterNotNull()

    override fun sampleInbox() = Puzzle.sampleInt(8)

    override fun sampleSolution() =
        Program {
            JUMP("c")
            LABEL("a")
            COPYFROM(0)
            LABEL("b")
            OUTBOX()
            LABEL("c")
            INBOX()
            COPYTO(0)
            INBOX()
            SUB(0)
            JUMPN("a")
            ADD(0)
            JUMP("b")
        }
}