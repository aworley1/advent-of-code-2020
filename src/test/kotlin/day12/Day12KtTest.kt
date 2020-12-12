package day12

import assertk.assertThat
import assertk.assertions.isEqualTo
import day12.Direction.E
import day12.Direction.N
import day12.Direction.S
import day12.Direction.W
import org.junit.jupiter.api.Test

internal class Day12KtTest {
    @Test
    fun `should move the boat in some directions`() {
        val boat = Boat(Coord(0, 0), E)

        assertThat(boat.move(E, 2)).isEqualTo(Boat(Coord(2, 0), E))
        assertThat(boat.move(W, 2)).isEqualTo(Boat(Coord(-2, 0), E))
        assertThat(boat.move(N, 2)).isEqualTo(Boat(Coord(0, 2), E))
        assertThat(boat.move(S, 2)).isEqualTo(Boat(Coord(0, -2), E))
    }

    @Test
    fun `should move boat forward by some amount`() {
        assertThat(Boat(Coord(0, 0), E).moveForwards(5))
            .isEqualTo(Boat(Coord(5, 0), E))

        assertThat(Boat(Coord(0, 0), N).moveForwards(3))
            .isEqualTo(Boat(Coord(0, 3), N))
    }

    @Test
    fun `should rotate the boat right`() {
        assertThat(Boat(Coord(0, 0), E).rotateRight(270).direction).isEqualTo(N)
        assertThat(Boat(Coord(0, 0), N).rotateRight(90).direction).isEqualTo(E)
        assertThat(Boat(Coord(0, 0), S).rotateRight(450).direction).isEqualTo(W)
    }

    @Test
    fun `should rotate the boat left`() {
        assertThat(Boat(Coord(0, 0), E).rotateLeft(270).direction).isEqualTo(S)
        assertThat(Boat(Coord(0, 0), N).rotateLeft(90).direction).isEqualTo(W)
        assertThat(Boat(Coord(0, 0), S).rotateLeft(450).direction).isEqualTo(E)
    }

    @Test
    fun `should solve part1 example`() {
        val input = listOf(
            "F10",
            "N3",
            "F7",
            "R90",
            "F11",
            )

        assertThat(solvePart1(input)).isEqualTo(25)
    }

    @Test
    fun `should add coordinates`() {
        assertThat(Coord(1, 1) + Coord(1, 3)).isEqualTo(Coord(2, 4))
    }

    @Test
    fun `should scale coordinates`() {
        assertThat(Coord(1, 1) * 3).isEqualTo(Coord(3, 3))
    }
}