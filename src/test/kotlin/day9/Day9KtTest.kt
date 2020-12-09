package day9

import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day9KtTest {
    @Test
    fun `should find number combinations`() {
        val input = listOf(3L, 8L, 15L)

        val result = numberCombinations(input)

        assertThat(result).containsOnly(11L, 18L, 23L)
    }

    @Test
    fun `should solve example`() {
        val input = listOf(
            35.toLong(),
            20.toLong(),
            15.toLong(),
            25.toLong(),
            47.toLong(),
            40.toLong(),
            62.toLong(),
            55.toLong(),
            65.toLong(),
            95.toLong(),
            102.toLong(),
            117.toLong(),
            150.toLong(),
            182.toLong(),
            127.toLong(),
            219.toLong(),
            299.toLong(),
            277.toLong(),
            309.toLong(),
            576.toLong(),
        )

        val result = solvePart1(input, 5)

        assertThat(result).isEqualTo(127)
    }

    @Test
    fun `should solve example 2`() {
        val input = listOf(
            35.toLong(),
            20.toLong(),
            15.toLong(),
            25.toLong(),
            47.toLong(),
            40.toLong(),
            62.toLong(),
            55.toLong(),
            65.toLong(),
            95.toLong(),
            102.toLong(),
            117.toLong(),
            150.toLong(),
            182.toLong(),
            127.toLong(),
            219.toLong(),
            299.toLong(),
            277.toLong(),
            309.toLong(),
            576.toLong(),
        )

        val result = solvePart2(input, 127)

        assertThat(result).isEqualTo(62)
    }
}