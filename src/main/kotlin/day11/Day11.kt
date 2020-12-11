package day11

import common.readPuzzleInput
import day11.State.EMPTY
import day11.State.FLOOR
import day11.State.OCCUPIED

typealias NeighbourFinder = List<Space>.(Space) -> List<Space>

fun main() {
    println(solvePart1(readPuzzleInput("day11.txt")))
    println(solvePart2(readPuzzleInput("day11.txt")))
}

fun solvePart1(input: List<String>) = solve(input, List<Space>::neighbourFinder, 4)
fun solvePart2(input: List<String>) = solve(input, List<Space>::neighbourFinderPart2, 5)

fun solve(input: List<String>, neighbourFinder: NeighbourFinder, neighbourThreshold: Int): Int {
    var plan = parse(input)
    while (true) {
        val newPlan = plan.next(neighbourFinder, neighbourThreshold)
        if (plan == newPlan) return plan.count { it.state == OCCUPIED }
        plan = newPlan
    }
}

fun parse(input: List<String>): List<Space> {
    return input.flatMapIndexed { y, row ->
        row.mapIndexed { x, space ->
            Space(x, y, State.from(space))
        }
    }
}

fun List<Space>.next(
    findNeighbours: NeighbourFinder = List<Space>::neighbourFinder,
    neighbourThreshold: Int = 4
): List<Space> = this.map {
    val neighbours = findNeighbours(it)
    when (it.state) {
        OCCUPIED -> if (neighbours.count { it.state == OCCUPIED } >= neighbourThreshold) it.switch() else it
        EMPTY -> if (neighbours.none { it.state == OCCUPIED }) it.switch() else it
        FLOOR -> it
    }
}

fun List<Space>.neighbourFinder(space: Space): List<Space> = this.filter {
    it.x >= space.x - 1 && it.x <= space.x + 1 &&
            it.y >= space.y - 1 && it.y <= space.y + 1
}.filterNot { it.x == space.x && it.y == space.y }

fun List<Space>.neighbourFinderPart2(space: Space): List<Space> =
    Direction.values()
        .mapNotNull { direction ->
            val spacesInDirection = generateSequence(space) { currentSpace ->
                this.singleOrNull { it.x == currentSpace.x + direction.x && it.y == currentSpace.y + direction.y }
            }

            spacesInDirection.filterNot { it == space }
                .firstOrNull { it.state.isSeat() }
        }

fun List<Space>.toDisplay(): List<String> = this.groupBy { it.y }
    .toSortedMap()
    .map {
        it.value
            .sortedBy { it.x }
            .map { it.state.code }.joinToString("")
    }

data class Space(val x: Int, val y: Int, val state: State) {
    fun switch() = when (state) {
        EMPTY -> this.copy(state = OCCUPIED)
        OCCUPIED -> this.copy(state = EMPTY)
        FLOOR -> this
    }
}

enum class State(val code: Char) {
    EMPTY('L'), OCCUPIED('#'), FLOOR('.');

    fun isSeat(): Boolean {
        return this == EMPTY || this == OCCUPIED
    }

    companion object {
        fun from(char: Char): State = values().singleOrNull { it.code == char }
            ?: throw RuntimeException("Invalid code")
    }
}

enum class Direction(val x: Int, val y: Int) {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UPLEFT(-1, 1),
    UPRIGHT(1, 1),
    DOWNLEFT(-1, -1),
    DOWNRIGHT(1, -1),
}