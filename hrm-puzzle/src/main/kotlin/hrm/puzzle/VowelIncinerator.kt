package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-29 10:20:29
 */
object VowelIncinerator : Puzzle<Any?> {
    override val numbering = 34

    override val evaluation = Evaluation(13, 323)

    override fun rule(inbox: List<Any?>) = inbox.filter { it !in listOf('A', 'E', 'I', 'O', 'U') }

    override fun sampleInbox() = Puzzle.sampleChar(10)

    override fun sampleTiles() =
        listOf('A', 'E', 'I', 'O', 'U', 0)
            .withIndex()
            .associate { it.index to it.value }

    override fun sampleSolution() =
        Program {
            JUMP("b")
            LABEL("a")
            COPYFROM(6)
            OUTBOX()
            LABEL("b")
            COPYFROM(5)
            LABEL("c")
            COPYTO(9)
            INBOX()
            COPYTO(6)
            LABEL("d")
            COPYFROM_D(9)
            JUMPZ("a")
            SUB(6)
            JUMPZ("c")
            BUMPUP(9)
            JUMP("d")
        }

}