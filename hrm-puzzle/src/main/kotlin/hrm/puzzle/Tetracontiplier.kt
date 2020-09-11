package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-24 10:06:14
 */
object Tetracontiplier : Puzzle<Int> {
    override val numbering = 12

    override val evaluation = Evaluation(14, 56)

    override fun rule(inbox: List<Int>) = inbox.map { it * 40 }

    override fun sampleInbox() = Puzzle.sampleInt(4)

    override fun sampleSolution() =
        Program {
            LABEL("a")
            INBOX()
            COPYTO(0)
            ADD(0)
            COPYTO(0)
            ADD(0)
            COPYTO(0)
            ADD(0)
            COPYTO(0)
            ADD(0)
            ADD(0)
            ADD(0)
            ADD(0)
            OUTBOX()
            JUMP("a")
        }
}