package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-09-10 19:21:50
 */
object ReCoordinator : Puzzle<Int> {
    override val numbering = 39

    override val evaluation = Evaluation(14, 76)

    override fun rule(inbox: List<Int>) =
        inbox.map { listOf(it % 4, it / 4) }.flatten()

    override fun sampleInbox() = Puzzle.sampleInt(4, 0, 16)

    override fun sampleTiles() = mapOf(14 to 0, 15 to 4)

    override fun sampleSolution() =
        Program {
            JUMP("b")
            LABEL("a")
            ADD(15)
            OUTBOX()
            COPYFROM(1)
            OUTBOX()
            LABEL("b")
            COPYFROM(14)
            COPYTO(1)
            INBOX()
            LABEL("c")
            SUB(15)
            JUMPN("a")
            COPYTO(0)
            BUMPUP(1)
            COPYFROM(0)
            JUMP("c")
        }
}