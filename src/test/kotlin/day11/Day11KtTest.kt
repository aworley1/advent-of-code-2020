package day11

import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import day11.State.EMPTY
import day11.State.FLOOR
import day11.State.OCCUPIED
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

    @Test
    fun `should convert plan to strings`() {
        val plan = listOf(
            Space(2, 1, EMPTY),
            Space(0, 0, OCCUPIED),
            Space(0, 1, FLOOR),
            Space(1, 0, FLOOR),
            Space(1, 1, OCCUPIED),
            Space(2, 0, EMPTY),
        )

        assertThat(plan.toDisplay()).isEqualTo(
            listOf(
                "#.L",
                ".#L"
            )
        )
    }

    @Test
    fun `should switch empty seat to occupied if no adjacent seats are occupied`() {
        val input1 = parse(
            listOf(
                "LLL",
                "LLL",
                "LLL",
            )
        )

        val input2 = parse(
            listOf(
                "...",
                ".L.",
                "...",
            )
        )

        val result1 = input1.next()
        val result2 = input2.next()

        assertThat(result1.toDisplay()).isEqualTo(
            listOf(
                "###",
                "###",
                "###",
            )
        )

        assertThat(result2.toDisplay()).isEqualTo(
            listOf(
                "...",
                ".#.",
                "...",
            )
        )
    }

    @Test
    fun `should not become occupied if any neighbours are occupied`() {
        val input = parse(
            listOf(
                "...",
                ".L#",
                "...",
            )
        )

        val result = input.next().single { it.x == 1 && it.y == 1 }.state

        assertThat(result).isEqualTo(EMPTY)
    }

    @Test
    fun `should not become empty if fewer than 4 neighbours are occupied`() {
        val input = parse(
            listOf(
                "L..",
                ".##",
                ".##",
            )
        )

        val result = input.next().single { it.x == 1 && it.y == 1 }.state

        assertThat(result).isEqualTo(OCCUPIED)
    }

    @Test
    fun `should become empty if 4 or more neighbours are occupied`() {
        val input = parse(
            listOf(
                "#..",
                ".##",
                ".##",
            )
        )

        val result = input.next().single { it.x == 1 && it.y == 1 }.state

        assertThat(result).isEqualTo(EMPTY)
    }

    @Test
    fun `should find neighbours of a square`() {
        val plan = parse(
            listOf(
                "...",
                ".#.",
                "...",
            )
        )


        val neighbours = plan.neighbourFinder(Space(1, 1, OCCUPIED))

        assertThat(neighbours.map { it.x to it.y }).containsOnly(
            0 to 0, 0 to 1, 0 to 2,
            1 to 0, 1 to 2,
            2 to 0, 2 to 1, 2 to 2
        )
    }

    @Test
    fun `should solve part 1 example`() {
        val input = listOf(
            "#.##.##.##",
            "#######.##",
            "#.#.#..#..",
            "####.##.##",
            "#.##.##.##",
            "#.#####.##",
            "..#.#.....",
            "##########",
            "#.######.#",
            "#.#####.##",
        )

        assertThat(solvePart1(input)).isEqualTo(37)
    }

    @Test
    fun `should find neighbours which are not in adjacent squares`() {
        val input = parse(
            listOf(
                "#.#.#",
                ".....",
                "#.#.#",
                ".....",
                "#.#.#",
            )
        )

        val result = input.neighbourFinderPart2(Space(2, 2, OCCUPIED))

        assertThat(result).containsOnly(
            Space(0, 0, OCCUPIED),
            Space(2, 0, OCCUPIED),
            Space(4, 0, OCCUPIED),
            Space(0, 2, OCCUPIED),
            Space(4, 2, OCCUPIED),
            Space(0, 4, OCCUPIED),
            Space(2, 4, OCCUPIED),
            Space(4, 4, OCCUPIED),
        )
    }

    @Test
    fun `should handle no neighbours`() {
        val input = parse(listOf("..."))

        assertThat(input.neighbourFinderPart2(Space(0, 0, FLOOR))).isEmpty()

    }
}