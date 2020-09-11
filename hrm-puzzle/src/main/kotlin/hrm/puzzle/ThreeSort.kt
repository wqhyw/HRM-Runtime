package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-27 15:34:38
 */
object ThreeSort : Puzzle<Int> {
    override val numbering = 28

    override val evaluation = Evaluation(34, 78)

    override fun rule(inbox: List<Int>) = inbox.chunked(3) { it.sorted() }.flatten()

    override fun sampleInbox() = Puzzle.sampleInt(9)

    override fun sampleSolution() =
        Program {
            LABEL("a")
            INBOX()
            COPYTO(0)
            INBOX()
            COPYTO(2)
            INBOX()
            LABEL("b")
            COPYTO(1)
            SUB(2)
            JUMPN("c")
            COPYTO(1)
            ADD(2)
            COPYTO(2)
            SUB(1)
            COPYTO(1)
            LABEL("c")
            COPYFROM(1)
            SUB(0)
            JUMPN("d")
            COPYFROM(0)
            OUTBOX()
            COPYFROM(1)
            OUTBOX()
            COPYFROM(2)
            OUTBOX()
            JUMP("a")
            LABEL("d")
            COPYTO(1)
            ADD(0)
            COPYTO(0)
            SUB(1)
            JUMP("b")
        }
}