package hrm.puzzle

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator
import org.junit.jupiter.api.Test
import java.lang.reflect.Method

/**
 *  @author wqhyw
 *  @date 2020-08-19 18:01:51
 */
private class PuzzleNameDisPlayGenerator : DisplayNameGenerator.Standard() {
    override fun generateDisplayNameForMethod(testClass: Class<*>?, testMethod: Method): String? {
        val objName = testMethod.name
            .replace("Test ", "")

        val numbering = (Class.forName("hrm.puzzle.${objName.replace(" ", "")}")
            .kotlin
            .objectInstance as Puzzle<*>)
            .numbering
        return "[Puzzle No.${if (numbering < 10) "0$numbering" else numbering}: $objName]"
    }
}

@DisplayNameGeneration(PuzzleNameDisPlayGenerator::class)
class SampleSolutionTests {

    @Test
    fun `Test Mail Room`() {
        solve(MailRoom)
    }

    @Test
    fun `Test Busy Mail Room`() {
        solve(BusyMailRoom)
    }

    @Test
    fun `Test Copy Floor`() {
        solve(CopyFloor)
    }

    @Test
    fun `Test Scrambler Handler`() {
        solve(ScramblerHandler)
    }

    @Test
    fun `Test Rainy Summer`() {
        solve(RainySummer)
    }

    @Test
    fun `Test Zero Exterminator`() {
        solve(ZeroExterminator)
    }

    @Test
    fun `Test Tripler Room`() {
        solve(TriplerRoom)
    }

    @Test
    fun `Test Zero Preservation Initiative`() {
        solve(ZeroPreservationInitiative)
    }

    @Test
    fun `Test Octoplier Suite`() {
        solve(OctoplierSuite)
    }

    @Test
    fun `Test Sub Hallway`() {
        solve(SubHallway)
    }

    @Test
    fun `Test Tetracontiplier`() {
        solve(Tetracontiplier)
    }

    @Test
    fun `Test Equalization Room`() {
        solve(EqualizationRoom)
    }

    @Test
    fun `Test Maximization Room`() {
        solve(MaximizationRoom)
    }

    @Test
    fun `Test Absolute Positivity`() {
        solve(AbsolutePositivity)
    }

    @Test
    fun `Test Exclusive Lounge`() {
        solve(ExclusiveLounge)
    }

    @Test
    fun `Test Countdown`() {
        solve(Countdown)
    }

    @Test
    fun `Test Multiplication Workshop`() {
        solve(MultiplicationWorkshop)
    }

    @Test
    fun `Test Zero Terminated Sum`() {
        solve(ZeroTerminatedSum)
    }

    @Test
    fun `Test Fibonacci Visitor`() {
        solve(FibonacciVisitor)
    }

    @Test
    fun `Test The Littlest Number`() {
        solve(TheLittlestNumber)
    }

    @Test
    fun `Test Mod Module`() {
        solve(ModModule)
    }

    @Test
    fun `Test Cumulative Countdown`() {
        solve(CumulativeCountdown)
    }

    @Test
    fun `Test Small Divide`() {
        solve(SmallDivide)
    }

    @Test
    fun `Test Three Sort`() {
        solve(ThreeSort)
    }

    @Test
    fun `Test Storage Floor`() {
        solve(StorageFloor)
    }

    @Test
    fun `Test String Storage Floor`() {
        solve(StringStorageFloor)
    }

    @Test
    fun `Test String Reverse`() {
        solve(StringReverse)
    }

    @Test
    fun `Test Inventory Report`() {
        solve(InventoryReport)
    }

    @Test
    fun `Test Vowel Incinerator`() {
        solve(VowelIncinerator)
    }

    @Test
    fun `Test Duplicate Removal`() {
        solve(DuplicateRemoval)
    }

    @Test
    fun `Test Alphabetizer`() {
        solve(Alphabetizer)
    }

    @Test
    fun `Test Scavenger Chain`() {
        solve(ScavengerChain)
    }

    @Test
    fun `Test Digit Exploder`() {
        solve(DigitExploder)
    }

    @Test
    fun `Test ReCoordinator`() {
        solve(ReCoordinator)
    }

    @Test
    fun `Test Prime Factory`() {
//        solve(PrimeFactory)
    }

    private fun <E> solve(puzzle: Puzzle<E>) {
        val (inbox, outbox, succeed, actual) = puzzle.trySolve()

        val reference = puzzle.evaluation

        println("inbox = $inbox")
        println("Size ${actual.size}/${reference.size}, Speed ${actual.speed}/${reference.speed}")

        Assertions.assertEquals(puzzle.rule(inbox), outbox)
        Assertions.assertTrue(succeed)
    }
}