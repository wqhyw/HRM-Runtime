package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program
import kotlin.math.abs

/**
 *  @author wqhyw
 *  @date 2020-08-24 11:37:49
 */
object AbsolutePositivity : Puzzle<Int> {
    override val numbering = 16

    override val evaluation = Evaluation(8, 36)

    override fun rule(inbox: List<Int>) = inbox.map { abs(it) }

    override fun sampleInbox() = Puzzle.sampleInt(7)

    override fun sampleSolution() =
        Program {
            LABEL("a")
            INBOX()
            LABEL("b")
            COPYTO(0)
            SUB(0)
            SUB(0)
            JUMPN("b")
            OUTBOX()
            JUMP("a")
        }
}