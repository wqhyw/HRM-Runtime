package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-21 16:56:49
 */
object OctoplierSuite : Puzzle<Int> {
    override val numbering = 10

    override val evaluation = Evaluation(9, 36)

    override fun rule(inbox: List<Int>) = inbox.map { it * 8 }

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
            OUTBOX()
            JUMP("a")
        }
}