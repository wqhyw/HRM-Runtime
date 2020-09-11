package hrm.puzzle

import hrm.engine.Evaluation
import hrm.engine.Program
import hrm.puzzle.Puzzle.Companion.chunkedBy0

/**
 *  @author wqhyw
 *  @date 2020-08-31 17:49:25
 */
object Alphabetizer : Puzzle<Any> {
    override val numbering = 36

    override val evaluation = Evaluation(39, 109)

    override fun rule(inbox: List<Any>) =
        inbox
            .chunkedBy0 { it.joinToString(separator = "") }
            .minOrNull()
            ?.toList()
            ?: listOf()

    override fun sampleInbox() =
        listOf(
            listOf<Any>('U', 'N', 'I', 'X', 0, 'U', 'N', 'T', 'I', 'E', 0),
            listOf<Any>('U', 'N', 'D', 'O', 0, 'U', 'N', 'C', 'L', 'E', 0),
            listOf<Any>('U', 'N', 'S', 'E', 'T', 0, 'U', 'N', 'D', 'O', 0),
            listOf<Any>('U', 'N', 'D', 'E', 'R', 0, 'U', 'N', 'S', 'E', 'T', 0),
            listOf<Any>('U', 'N', 'I', 'T', 'E', 0, 'U', 'N', 'I', 'O', 'N', 0),
            listOf<Any>('U', 'N', 'C', 'A', 'P', 0, 'U', 'N', 0),
            listOf<Any>('U', 'N', 0, 'U', 'N', 'T', 'I', 'E', 0),
            listOf<Any>('D', 'I', 'R', 'T', 0, 'U', 'N', 'C', 'A', 'P', 0),
            listOf<Any>('U', 'N', 'Z', 'I', 'P', 0, 'D', 'I', 'R', 'T', 0),
            listOf<Any>('U', 'N', 'I', 'T', 'S', 0, 'U', 'N', 'I', 'T', 0),
            listOf<Any>('U', 'N', 'D', 'O', 0, 'U', 'N', 'I', 'T', 0),
            listOf<Any>('D', 'I', 'R', 'T', 0, 'U', 'N', 0),
            listOf<Any>('D', 'I', 'R', 'T', 0, 'D', 'I', 'R', 'T', 0),
            listOf<Any>('U', 'N', 'C', 'L', 'E', 0, 'U', 'N', 'C', 'L', 'E', 0),
            listOf<Any>('U', 'N', 0, 'D', 'I', 'R', 'T', 0)
        ).random()

    override fun sampleTiles() = mapOf(23 to 0, 24 to 10)

    override fun sampleSolution() =
        Program {
            LABEL("a")
            INBOX()
            COPYTO_D(23)
            JUMPZ("e")
            BUMPUP(23)
            JUMP("a")
            LABEL("b")
            COPYTO(24)
            LABEL("c")
            LABEL("d")
            COPYFROM_D(23)
            JUMPZ("g")
            OUTBOX()
            BUMPUP(23)
            LABEL("e")
            COPYTO(23)
            COPYFROM(24)
            JUMPN("d")
            INBOX()
            JUMPZ("j")
            COPYTO(10)
            COPYFROM_D(23)
            JUMPZ("i")
            SUB(10)
            JUMPZ("c")
            JUMPN("b")
            COPYFROM(10)
            LABEL("f")
            OUTBOX()
            INBOX()
            JUMPZ("h")
            JUMP("f")
            LABEL("g")
            LABEL("h")
            LABEL("i")
            LABEL("j")
        }

}
