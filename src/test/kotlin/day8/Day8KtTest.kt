package day8

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day8KtTest {
//    @Test
//    fun `should solve example 1`() {
//        val input = listOf(
//            "nop +0",
//            "acc +1",
//            "jmp +4",
//            "acc +3",
//            "jmp -3",
//            "acc -99",
//            "acc +1",
//            "jmp -4",
//            "acc +6",
//        )
//
//        val result = solvePart1String(input)
//
//        assertThat(result).isEqualTo(5)
//    }

    @Test
    fun `should solve example 2`() {
        val input = listOf(
            "nop +0",
            "acc +1",
            "jmp +4",
            "acc +3",
            "jmp -3",
            "acc -99",
            "acc +1",
            "jmp -4",
            "acc +6",
        )

        val result = solvePart2(input)

        assertThat(result).isEqualTo(8)
    }
}