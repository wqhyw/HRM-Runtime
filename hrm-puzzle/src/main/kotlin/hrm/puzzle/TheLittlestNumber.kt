package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program
import hrm.puzzle.Puzzle.Companion.chunkedBy0

/**
 *  @author wqhyw
 *  @date 2020-08-25 16:48:44
 */
object TheLittlestNumber : Puzzle<Int> {
    override val numbering = 23

    override val evaluation = Evaluation(13, 75)

    override fun rule(inbox: List<Int>) = inbox.chunkedBy0 { it.minOrNull() }.filterNotNull()

    override fun sampleInbox() = listOf(8, 15, 2, 0, 19, 14, 8, 4, 0, 57, 47, 20, 44, 40, 0)

    override fun sampleSolution() =
        Program {
            JUMP("b")
            LABEL("a")
            COPYFROM(0)
            OUTBOX()
            LABEL("b")
            INBOX()
            JUMP("d")
            LABEL("c")
            ADD(0)
            LABEL("d")
            COPYTO(0)
            LABEL("e")
            INBOX()
            JUMPZ("a")
            SUB(0)
            JUMPN("c")
            JUMP("e")
        }
}