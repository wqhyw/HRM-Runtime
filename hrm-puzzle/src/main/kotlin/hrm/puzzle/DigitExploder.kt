package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-09-10 18:52:45
 */
object DigitExploder : Puzzle<Int> {
    override val numbering = 38

    override val evaluation =  Evaluation(30, 165)

    override fun rule(inbox: List<Int>) =
        inbox.map { it.toString().toList() }
            .flatten()
            .map { it - '0' }

    override fun sampleInbox() = listOf(705, 8, 60, 744)

    override fun sampleTiles() = mapOf(9 to 0, 10 to 10, 11 to 100)

    override fun sampleSolution() =
        Program {
            LABEL("a")
            INBOX()
            COPYTO(1)
            LABEL("b")
            BUMPUP(9)
            COPYTO(6)
            COPYFROM_D(9)
            JUMPZ("d")
            BUMPUP(6)
            SUB(6)
            COPYTO_D(6)
            LABEL("c")
            COPYFROM_D(9)
            SUB(10)
            JUMPN("b")
            COPYTO_D(9)
            BUMPUP_D(6)
            JUMP("c")
            LABEL("d")
            LABEL("e")
            BUMPDN(9)
            JUMPZ("a")
            COPYFROM_D(9)
            OUTBOX()
            JUMP("e")
        }
}