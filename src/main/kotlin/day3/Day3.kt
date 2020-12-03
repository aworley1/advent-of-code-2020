package day3

import common.readPuzzleInput

fun solvePuzzlePart1(input: List<String>): Int {
    val stepRightIterator = generateSequence(0) { it + 3 }.iterator()

    return TobogganMap(input)
        .map { row ->        row[stepRightIterator.next()]    }
        .count { it == Square.TREE }
}

enum class Square {
    TREE, GAP
}

class TobogganMap(private val input: List<String>): Iterable<Row> {
    operator fun get(rowIndex: Int): Row = Row(input[rowIndex])

    override fun iterator(): Iterator<Row> {
        return input.map { Row(it) }.iterator()
    }
}

data class Row(private val row: String) {
    operator fun get(colIndex: Int) =
        if (row[colIndex % row.length] == '#') Square.TREE else Square.GAP
}


fun main() {
    println(solvePuzzlePart1(readPuzzleInput("day3.txt")))
}