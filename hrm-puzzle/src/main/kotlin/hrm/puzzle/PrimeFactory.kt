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
        inbox.map { PrimeHelper.primeFactor(it) }.flatten()

    override fun sampleInbox() = Puzzle.sampleInt(3, 10, 20)

    override fun sampleSolution() =
        Program {
            LABEL("a")
            INBOX()
            LABEL("b")
            COPYTO(0)
            COPYTO(1)
            LABEL("c")
            COPYFROM(24)
            COPYTO(5)
            BUMPDN(1)
            JUMPZ("a")
            COPYFROM(0)
            LABEL("d")
            SUB(1)
            JUMPZ("e")
            JUMPN("c")
            COPYTO(2)
            BUMPUP(5)
            COPYFROM(2)
            JUMP("d")
            LABEL("e")
            BUMPUP(5)
            OUTBOX()
            COPYFROM(1)
            JUMP("b")
        }
}