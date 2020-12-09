package day9

import common.readPuzzleInput

fun main() {
    println(solvePart1(readPuzzleInput("day9.txt").map { it.toLong() }, 25))
}

fun numberCombinations(input: List<Long>): Set<Long> {
    return input.flatMap { outer ->
        input.map { inner ->
            Pair(inner, outer)
        }
    }.filterNot { it.first == it.second }
        .map { it.first + it.second }
        .toSet()
}

fun solvePart1(input: List<Long>, preambleSize: Int): Long {
    input.windowed(preambleSize + 1)
        .forEach {
            val preamble = it.take(preambleSize)
            val allowedNumbers = numberCombinations(preamble)

            val current = it.takeLast(1).single()
            if (!allowedNumbers.contains(current)) return current
        }

    throw RuntimeException("Not found")
}