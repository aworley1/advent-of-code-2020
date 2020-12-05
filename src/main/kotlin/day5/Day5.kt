package day5

import common.readPuzzleInput

fun part1(input: List<String>) = input.map { findSeatId(it) }.maxOrNull()

fun findSeatId(input: String): Int {
    val rowId = findRow(input.take(7))
    val columnId = findColumn(input.takeLast(3))

    return (rowId * 8) + columnId
}

fun findRow(rowInput: String): Int = binarySearch(rowInput, 'F', 0..127)
fun findColumn(columnInput: String): Int = binarySearch(columnInput, 'L', 0..7)

private fun binarySearch(input: String, lowerChar: Char, startRange: IntRange): Int {
    return input.fold(startRange.toList()) { acc, op ->
        if (op == lowerChar) acc.take(acc.size / 2)
        else acc.takeLast(acc.size / 2)
    }.single()
}

fun main() {
    println(part1(readPuzzleInput("day5.txt")))
}