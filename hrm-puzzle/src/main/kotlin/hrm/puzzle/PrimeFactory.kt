package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program
import hrm.helper.PrimeHelper

/**
 *  @author wqhyw
 *  @date 2020-09-10 19:30:29
 */
object PrimeFactory : Puzzle<Int> {
    override val numbering = 40

    override val evaluation = Evaluation(28, 399)

    override fun rule(inbox: List<Int>) =
        inbox.map(PrimeHelper::primeFactor).flatten()

    override fun sampleInbox() = Puzzle.sampleInt(3, 10, 20)

    override fun sampleTiles() = mapOf(24 to 0)

    override fun sampleSolution() =
        Program {
            LABEL("a")
            LABEL("b")
            INBOX()
            COPYTO(10)
            COPYFROM(24)
            COPYTO(22)
            BUMPUP(22)
            COPYTO(1)
            LABEL("c")
            BUMPUP(1)
            LABEL("d")
            COPYFROM(24)
            COPYTO(12)
            SUB(10)
            LABEL("e")
            COPYTO(11)
            BUMPUP(12)
            COPYFROM(11)
            ADD(1)
            JUMPN("e")
            JUMPZ("f")
            BUMPDN(22)
            JUMPZ("c")
            COPYFROM(10)
            OUTBOX()
            JUMP("b")
            LABEL("f")
            COPYFROM(1)
            OUTBOX()
            BUMPDN(12)
            JUMPZ("a")
            BUMPUP(12)
            COPYTO(10)
            JUMP("d")
        }
}