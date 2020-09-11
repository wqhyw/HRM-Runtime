package hrm.engine

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * @author wqhyw
 * @date 2020-09-11 14:21:30
 */
class ProgramTest {

    @Test
    fun `Test builder`() {
        val programStr = Program {
            INBOX()
            COPYTO(0)
            BUMPUP(0)
            ADD(0)
            OUTBOX()
        }.toString()

        Assertions.assertEquals(programStr, listOf("INBOX", "COPYTO\t0", "BUMPUP\t0", "ADD\t0", "OUTBOX").joinToString(separator = "\n"))
    }

}