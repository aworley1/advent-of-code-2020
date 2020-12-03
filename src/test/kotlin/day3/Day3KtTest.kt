package day3

import assertk.assertThat
import assertk.assertions.isEqualTo
import day3.Square.GAP
import day3.Square.TREE
import org.junit.jupiter.api.Test

internal class Day3KtTest {
    @Test
    fun `should parse input`() {
        val input = listOf(
            ".#.",
            "#.#",
        )

        val map = TobogganMap(input)

        assertThat(map[0][0]).isEqualTo(GAP)
        assertThat(map[0][1]).isEqualTo(TREE)
        assertThat(map[0][2]).isEqualTo(GAP)

        assertThat(map[1][0]).isEqualTo(TREE)
        assertThat(map[1][1]).isEqualTo(GAP)
        assertThat(map[1][2]).isEqualTo(TREE)
    }

    @Test
    fun `map should repeat to the right`() {
        val input = listOf(
            ".#.",
            "#.#",
        )

        val map = TobogganMap(input)

        assertThat(map[0][3]).isEqualTo(GAP)
        assertThat(map[0][4]).isEqualTo(TREE)
        assertThat(map[0][5]).isEqualTo(GAP)
        assertThat(map[0][6]).isEqualTo(GAP)
    }

    @Test
    fun `should solve sample problem`() {
        val input = listOf(
            "..##.......",
            "#...#...#..",
            ".#....#..#.",
            "..#.#...#.#",
            ".#...##..#.",
            "..#.##.....",
            ".#.#.#....#",
            ".#........#",
            "#.##...#...",
            "#...##....#",
            ".#..#...#.#",
        )

        assertThat(solvePuzzlePart1(input, 3, 1)).isEqualTo(7)
    }

    @Test
    fun `should be able to step down two rows at a time`() {
        val input = listOf(
            "....",
            "....",
            ".#..",
            "....",
            "..#.",
        )

        assertThat(solvePuzzlePart1(input, 1, 2)).isEqualTo(2)
    }

    @Test
    fun `should work out part 2 sample result for all slopes`() {
        val input = listOf(
            "..##.......",
            "#...#...#..",
            ".#....#..#.",
            "..#.#...#.#",
            ".#...##..#.",
            "..#.##.....",
            ".#.#.#....#",
            ".#........#",
            "#.##...#...",
            "#...##....#",
            ".#..#...#.#",
        )

        val slopes = listOf(
            Slope(1, 1),
            Slope(3, 1),
            Slope(5, 1),
            Slope(7, 1),
            Slope(1, 2),
        )

        assertThat(solvePuzzlePart2(input, slopes)).isEqualTo(336)
    }
}