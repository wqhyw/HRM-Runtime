package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-25 11:27:30
 */
object MultiplicationWorkshop : Puzzle<Int> {
    override val numbering = 20

    override val evaluation = Evaluation(15, 109)

    override fun rule(inbox: List<Int>) = inbox.chunked(2) { it[0] * it[1] }

    override fun sampleInbox() = Puzzle.sampleInt(10) { it > 0 }

    override fun sampleSolution() =
        Program {
            JUMP("b")
            LABEL("a")
            COPYFROM(2)
            OUTBOX()
            LABEL("b")
            INBOX()
            COPYTO(0)
            INBOX()
            COPYTO(1)
            SUB(1)
            LABEL("c")
            COPYTO(2)
            BUMPDN(1)
            JUMPN("a")
            COPYFROM(2)
            ADD(0)
            JUMP("c")
        }
}