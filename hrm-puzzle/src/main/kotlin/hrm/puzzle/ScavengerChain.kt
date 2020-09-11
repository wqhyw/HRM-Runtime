package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program

/**
 *  @author wqhyw
 *  @date 2020-09-10 18:31:31
 */
object ScavengerChain : Puzzle<Any?> {
    override val numbering = 37

    override val evaluation = Evaluation(8, 63)

    override fun rule(inbox: List<Any?>): List<Any?> {
        val floor = sampleTiles()
        val res = mutableListOf<Any?>()

        for (c in inbox) {
            res.add(floor[c])

            if (c is Int) {
                var nc = floor[c + 1]

                while (nc is Int && nc >= 0) {
                    res.add(floor[nc])
                    nc = floor[nc + 1]
                }
            }
        }

        return res
    }

    override fun sampleInbox() = listOf(0, 23).shuffled()

    override fun sampleTiles() =
        mapOf(
            0 to 'E',
            1 to 13,
            3 to 'C',
            4 to 23,
            10 to 'P',
            11 to 20,
            13 to 'S',
            14 to 3,
            20 to 'E',
            21 to -1,
            23 to 'A',
            24 to 10
        )

    override fun sampleSolution() =
        Program {
            LABEL("a")
            INBOX()
            LABEL("b")
            COPYTO(22)
            COPYFROM_D(22)
            OUTBOX()
            BUMPUP(22)
            COPYFROM_D(22)
            JUMPN("a")
            JUMP("b")
        }
}