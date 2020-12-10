package day10

import assertk.assertThat
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
        val input = listOf(
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

        assertThat(solvePart1(input)).isEqualTo(220)
    }
}