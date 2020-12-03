package day3

import common.readPuzzleInput

data class Slope(val right: Int, val down: Int)

fun main() {
    println(solvePuzzlePart1(readPuzzleInput("day3.txt"), 3, 1))

    val slopes = listOf(
        Slope(right = 1, down = 1),
        Slope(right = 3, down = 1),
        Slope(right = 5, down = 1),
        Slope(right = 7, down = 1),
        Slope(right = 1, down = 2),
    )
    println(solvePuzzlePart2(readPuzzleInput("day3.txt"), slopes))
}

fun solvePuzzlePart1(input: List<String>, stepRight: Int, stepDown: Int): Int {
    val stepRightIterator = generateSequence(0) { it + stepRight }.iterator()

    return TobogganMap(input)
        .mapIndexedNotNull { index, row ->
            if (index % stepDown == 0)
                row[stepRightIterator.next()]
            else null
        }
        .count { it == Square.TREE }
}

fun solvePuzzlePart2(input: List<String>, slopes: List<Slope>): Long {
    return slopes.map { solvePuzzlePart1(input, it.right, it.down) }
        .fold(1L) { acc, i -> acc * i }
}

class TobogganMap(private val input: List<String>) : Iterable<Row> {
    operator fun get(rowIndex: Int): Row = Row(input[rowIndex])

    override fun iterator(): Iterator<Row> {
        return input.map { Row(it) }.iterator()
    }
}

data class Row(private val row: String) {
    operator fun get(colIndex: Int) =
        if (row[colIndex % row.length] == '#') Square.TREE else Square.GAP
}

enum class Square {
    TREE, GAP
}
