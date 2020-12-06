package day6

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.containsOnly
import assertk.assertions.isEmpty
import org.junit.jupiter.api.Test

internal class Day6KtTest {
    @Test
    fun `should parse input`() {
        val stringInput = "abc\n" +
                "def\n" +
                "\n" +
                "fgh\n"

        val result = parse(stringInput)

        assertThat(result).containsExactly(listOf("abc", "def"), listOf("fgh"))
    }

    @Test
    fun `should find the number of distinct questions in each group`() {
        val groups = listOf(
            listOf("abc", "cde"),
            listOf("ab", "cd")
        )

        val result = distinctAnswersPerGroup(groups)

        assertThat(result[0]).containsOnly('a','b', 'c','d','e')
        assertThat(result[1]).containsOnly('a','b', 'c','d')
    }

    @Test
    fun `should find common answers in a group`() {
        val groups = listOf(
            listOf("eabc", "cde"),
            listOf("ab", "cd")
        )

        val result = commonAnswersPerGroup(groups)

        assertThat(result[0]).containsOnly('c', 'e')
        assertThat(result[1]).isEmpty()
    }
}