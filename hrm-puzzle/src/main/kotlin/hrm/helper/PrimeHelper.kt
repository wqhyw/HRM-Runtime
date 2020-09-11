package hrm.helper

import kotlin.math.abs
import kotlin.math.sqrt
import kotlin.random.Random

/**
 * @author wqhyw
 */
object PrimeHelper {
    private class FactorNotFoundException : RuntimeException()

    private fun findFac(n: Int): List<Int> =
        if (isPrime(n)) {
            listOf(n)
        } else {
            val first = generateSequence(n, ::pollardRho)
                .first { it < n }

            findFac(first) + findFac(n / first)
        }

    fun gcd(a: Int, b: Int) =
        generateSequence(if (a > b) a to b else b to a) { it.second to (it.first % it.second) }
            .first() { it.second == 0 }
            .first

    fun isPrime(n: Int) =
        (2..(sqrt(n.toDouble()).toInt())).none { n % it == 0 }

    fun pollardRho(n: Int): Int {
        var x = 2
        for (cycle in generateSequence(0) { it + 1 }) {
            val y = x
            repeat(2 shl cycle) {
                x = (x * x + Random.nextInt(n - 1) + 1) % n
                val factor = gcd(abs(x - y), n)
                if (factor > 1) {
                    return@pollardRho factor
                }
            }
        }

        throw FactorNotFoundException()
    }

    fun primeFactor(n: Int) = findFac(n).sorted()
}