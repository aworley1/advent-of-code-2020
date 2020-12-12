package day12

import common.readPuzzleInput
import day12.Direction.*
import kotlin.math.abs

fun main() {
    println(solvePart1(readPuzzleInput("day12.txt")))
}

fun solvePart1(input: List<String>): Int {
    var boat = Boat(Coord(0,0), E)

    input.forEach {
        val regex = "([A-Z])([0-9]*)".toRegex()
        val (action, amountStr) = regex.matchEntire(it)!!.destructured
        val amount = amountStr.toInt()
        when (action) {
            "N" -> boat = boat.move(N, amount)
            "S" -> boat = boat.move(S, amount)
            "E" -> boat = boat.move(E, amount)
            "W" -> boat = boat.move(W, amount)
            "F" -> boat = boat.moveForwards(amount)
            "L" -> boat = boat.rotateLeft(amount)
            "R" -> boat = boat.rotateRight(amount)
        }
    }

    return abs(boat.position.x) + abs(boat.position.y)
}

data class Coord(val x: Int, val y: Int) {
    operator fun plus(other: Coord): Coord =
        Coord(x + other.x, y + other.y)

    operator fun times(scale: Int): Coord =
        Coord(x * scale, y * scale)
}

data class Boat(val position: Coord, val direction: Direction) {
    fun move(direction: Direction, amount: Int): Boat {
        return this.copy(
            position = position + direction.delta * amount
        )
    }

    fun moveForwards(amount: Int): Boat {
        return this.copy(position = position + direction.delta * amount)
    }

    fun rotateRight(degrees: Int) = this.copy(direction = direction.rotateRight(degrees))
    fun rotateLeft(degrees: Int) = this.copy(direction = direction.rotateRight(360 - degrees))
}

enum class Direction(val delta: Coord, val compassDegrees: Int) {
    N(Coord(0, 1), 0),
    S(Coord(0, -1), 180),
    E(Coord(1, 0), 90),
    W(Coord(-1, 0), 270);

    fun rotateRight(degrees: Int): Direction {
        val newCompassDegrees = (compassDegrees + degrees) % 360
        return values().single { it.compassDegrees == newCompassDegrees }
    }
}