package day10

import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day10KtTest {
    @Test
    fun `should convert chain of adapters to part 1 output`() {
        //3 differences of 1 and 2 differences of 3
        val testChain = listOf(1, 2, 3, 6, 7, 10)

        val exampleChain = listOf(0, 1, 4, 5, 6, 7, 10, 11, 12, 15, 16, 19, 22)

        assertThat(chainToOutput(testChain)).isEqualTo(6)
        assertThat(chainToOutput(exampleChain)).isEqualTo(35)
    }

    @Test
    fun `should solve example part 1`() {
        assertThat(solvePart1(secondExample)).isEqualTo(220)
    }

    @Test
    fun `should find adapters that are valid next`() {
        val input = listOf(1, 2, 3, 4, 5, 6)

        assertThat(validNext(1, input)).containsOnly(2, 3, 4)
    }

    @Test
    fun `should build map of adapters and valid next adapters`() {
        val input = listOf(1, 2, 3, 5, 6, 7, 10)
        val result = buildValidNextAdapters(input)

        assertThat(result.size).isEqualTo(input.size)
        assertThat(result[1]!!).containsOnly(2, 3)
        assertThat(result[2]!!).containsOnly(3, 5)
        assertThat(result[3]!!).containsOnly(5, 6)
        assertThat(result[5]!!).containsOnly(6, 7)
        assertThat(result[6]!!).containsOnly(7)
        assertThat(result[7]!!).containsOnly(10)
    }

    @Test
    fun `should solve first part2 example`() {
        val input = listOf(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4)

        assertThat(solvePart2(input)).isEqualTo(8)
    }

    @Test
    fun `should solve second part2 example`() {
        assertThat(solvePart2(secondExample)).isEqualTo(19208)
    }

    @Test
    fun `should find number of combinations working backwards from biggest number`() {
        val list = listOf(0, 1, 2, 7, 3, 6)

        val result = numberOfCombinationsForEachNumber(list)

        assertThat(result[7]).isEqualTo(1L)
        assertThat(result[6]).isEqualTo(1L)
        assertThat(result[3]).isEqualTo(1L)
        assertThat(result[2]).isEqualTo(1L)
        assertThat(result[1]).isEqualTo(2L)
        assertThat(result[0]).isEqualTo(4L)
    }

    private val secondExample = listOf(
        28,
        33,
        18,
        42,
        31,
        14,
        46,
        20,
        48,
        47,
        24,
        23,
        49,
        45,
        19,
        38,
        39,
        11,
        1,
        32,
        25,
        35,
        8,
        17,
        7,
        9,
        4,
        2,
        34,
        10,
        3
    )

}