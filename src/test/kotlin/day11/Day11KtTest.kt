package day11

import assertk.assertThat
import assertk.assertions.containsOnly
import day11.State.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day11KtTest {
    @Test
    fun `should parse input`() {
        val input = listOf(
            "#.L",
            ".#L"
        )

        assertThat(parse(input)).containsOnly(
            Space(0, 0, OCCUPIED),
            Space(1, 0, FLOOR),
            Space(2, 0, EMPTY),
            Space(0, 1, FLOOR),
            Space(1, 1, OCCUPIED),
            Space(2, 1, EMPTY),
        )
    }
}