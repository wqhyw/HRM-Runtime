package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-25 16:57:00
 */
object ModModule : Puzzle<Int> {
    override val numbering = 24

    override val evaluation = Evaluation(12, 57)

    override fun rule(inbox: List<Int>) = inbox.chunked(2) { it[0] % it[1] }

    override fun sampleInbox() = listOf(5, 2, 6, 2, 4, 6, 0, 8)

    override fun sampleSolution() =
        Program {
            JUMP("b")
            LABEL("a")
            ADD(1)
            OUTBOX()
            LABEL("b")
            INBOX()
            COPYTO(0)
            INBOX()
            COPYTO(1)
            COPYFROM(0)
            LABEL("c")
            SUB(1)
            JUMPN("a")
            JUMP("c")
        }
}