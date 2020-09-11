package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-08-25 12:26:20
 */
object FibonacciVisitor : Puzzle<Int> {
    override val numbering = 22

    override val evaluation = Evaluation(19, 156)

    override fun rule(inbox: List<Int>) =
        inbox.map {
            generateSequence(1 to 1) { inner ->
                if (inner.second <= it) inner.second to inner.first + inner.second else null
            }.map { inner ->
                inner.first
            }.toList()
        }.flatten()

    override fun sampleInbox() = listOf(5, 20)

    override fun sampleTiles() = mapOf(9 to 0)

    override fun sampleSolution() =
        Program {
            BUMPUP(9)
            LABEL("a")
            INBOX()
            COPYTO(8)
            COPYFROM(9)
            COPYTO(3)
            LABEL("b")
            COPYTO(4)
            OUTBOX()
            COPYFROM(8)
            SUB(3)
            JUMPN("a")
            COPYFROM(3)
            ADD(4)
            COPYTO(3)
            SUB(4)
            JUMP("b")
        }
}