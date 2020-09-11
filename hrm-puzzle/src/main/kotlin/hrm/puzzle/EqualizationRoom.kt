package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-24 10:10:03
 */
object EqualizationRoom : Puzzle<Int> {
    override val numbering = 13

    override val evaluation = Evaluation(9, 27)

    override fun rule(inbox: List<Int>) =
        inbox
            .chunked(2) { if (it[0] == it[1]) it[0] else null }
            .filterNotNull()

    override fun sampleInbox() = Puzzle.sampleInt(8)

    override fun sampleSolution() =
        Program {
            JUMP("b")
            LABEL("a")
            COPYFROM(0)
            OUTBOX()
            LABEL("b")
            LABEL("c")
            INBOX()
            COPYTO(0)
            INBOX()
            SUB(0)
            JUMPZ("a")
            JUMP("c")
        }
}