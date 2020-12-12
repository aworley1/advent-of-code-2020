package day12

import common.readPuzzleInput
import day12.Direction.E
import day12.Direction.N
import day12.Direction.S
import day12.Direction.W
import day12.Rotate.*
import kotlin.math.abs

fun main() {
    println(solvePart1(readPuzzleInput("day12.txt")))
    println(solvePart2(readPuzzleInput("day12.txt")))
}

fun solvePart1(input: List<String>): Int {
    var boat = Boat(Coord(0, 0), E)

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

fun solvePart2(input: List<String>): Int {
    var boatAndWaypoint = BoatAndWaypoint(
        Boat(Coord(0, 0), E),
        Boat(Coord(10, 1), E)
    )

    input.forEach {
        val regex = "([A-Z])([0-9]*)".toRegex()
        val (action, amountStr) = regex.matchEntire(it)!!.destructured
        val amount = amountStr.toInt()
        when (action) {
            "N" -> boatAndWaypoint = boatAndWaypoint.moveWaypoint(N, amount)
            "S" -> boatAndWaypoint = boatAndWaypoint.moveWaypoint(S, amount)
            "E" -> boatAndWaypoint = boatAndWaypoint.moveWaypoint(E, amount)
            "W" -> boatAndWaypoint = boatAndWaypoint.moveWaypoint(W, amount)
            "F" -> boatAndWaypoint = boatAndWaypoint.moveBoatToWaypoint(amount)
            "L" -> boatAndWaypoint = boatAndWaypoint.rotateWaypoint(amount, LEFT)
            "R" -> boatAndWaypoint = boatAndWaypoint.rotateWaypoint(amount, RIGHT)
        }
    }

    println(boatAndWaypoint)

    return abs(boatAndWaypoint.boat.position.x) + abs(boatAndWaypoint.boat.position.y)
}

data class Coord(val x: Int, val y: Int) {
    operator fun plus(other: Coord): Coord =
        Coord(x + other.x, y + other.y)

    operator fun times(scale: Int): Coord =
        Coord(x * scale, y * scale)

    operator fun minus(other: Coord) =
        Coord(x - other.x, y - other.y)
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

enum class Rotate { LEFT, RIGHT }

data class BoatAndWaypoint(
    val boat: Boat,
    val waypoint: Boat
) {
    fun moveBoatToWaypoint(number: Int): BoatAndWaypoint {
        val newBoatPosition = (waypoint.position - boat.position) * number + boat.position
        val newWaypointPosition = newBoatPosition + waypoint.position - boat.position
        return BoatAndWaypoint(
            boat = boat.copy(position = newBoatPosition),
            waypoint = waypoint.copy(position = newWaypointPosition)
        )
    }

    fun rotateWaypoint(degrees: Int, direction: Rotate) = this.copy(
        waypoint = waypoint.copy(
            position = rotateAboutOrigin(waypoint.position, boat.position, degrees, direction)
        )
    )

    fun moveWaypoint(direction: Direction, amount: Int) =
        this.copy(waypoint = waypoint.move(direction, amount))
}

private fun rotateAboutOrigin(coord: Coord, origin: Coord, degrees: Int, direction: Rotate): Coord {
    val timesToRotate = when (direction) {
        LEFT -> ((360 - degrees) / 90)
        RIGHT -> degrees / 90
    }

    println(timesToRotate)

    return (1..timesToRotate).fold(coord) { acc, _ -> rotate90RightAboutOrigin(acc, origin) }
}

private fun rotate90RightAboutOrigin(coord: Coord, origin: Coord): Coord {
    val relativeToOrigin = coord - origin
    val rotated = when {
        relativeToOrigin.x > 0 && relativeToOrigin.y > 0 -> Coord(relativeToOrigin.x, -relativeToOrigin.y)
        relativeToOrigin.x < 0 && relativeToOrigin.y < 0 -> Coord(relativeToOrigin.x, -relativeToOrigin.y)
        relativeToOrigin.x > 0 && relativeToOrigin.y < 0 -> Coord(-relativeToOrigin.x, relativeToOrigin.y)
        relativeToOrigin.x < 0 && relativeToOrigin.y > 0 -> Coord(-relativeToOrigin.x, relativeToOrigin.y)
        else -> relativeToOrigin
    }
    return rotated + origin
}