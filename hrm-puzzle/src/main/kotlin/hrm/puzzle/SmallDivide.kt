package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-26 17:27:10
 */
object SmallDivide : Puzzle<Int> {
    override val numbering = 26

    override val evaluation = Evaluation(15, 76)

    override fun rule(inbox: List<Int>) = inbox.chunked(2) { it[0] / it[1] }

    override fun sampleInbox() = listOf(9, 3, 7, 3, 3, 6, 0, 9)

    override fun sampleTiles() = mapOf(9 to 0)

    override fun sampleSolution() =
        Program {
            JUMP("b")
            LABEL("a")
            COPYFROM(8)
            OUTBOX()
            LABEL("b")
            INBOX()
            COPYTO(0)
            INBOX()
            COPYTO(1)
            COPYFROM(9)
            COPYTO(8)
            LABEL("c")
            COPYFROM(0)
            SUB(1)
            JUMPN("a")
            COPYTO(0)
            BUMPUP(8)
            JUMP("c")
        }
}