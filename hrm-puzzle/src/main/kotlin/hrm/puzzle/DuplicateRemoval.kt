package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-31 17:21:02
 */
object DuplicateRemoval : Puzzle<Any?> {
    override val numbering = 35

    override val evaluation = Evaluation(17, 167)

    override fun rule(inbox: List<Any?>) = inbox.distinct()

    override fun sampleInbox() = listOf('A', 'C', 'E', 'E', 'B', 'C', 'C', 'A', 'D', 'E')

    override fun sampleTiles() = mapOf(14 to 0)

    override fun sampleSolution() =
        Program {
            JUMP("b")
            LABEL("a")
            COPYFROM_D(14)
            OUTBOX()
            LABEL("b")
            LABEL("c")
            BUMPUP(14)
            COPYTO(13)
            INBOX()
            COPYTO_D(14)
            LABEL("d")
            BUMPDN(13)
            JUMPZ("a")
            COPYFROM_D(14)
            SUB_D(13)
            JUMPZ("c")
            JUMP("d")
        }
}