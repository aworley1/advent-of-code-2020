package day10

import common.readPuzzleInput
import java.lang.RuntimeException

fun chainToOutput(chain: List<Int>): Int {
    val differences = chain.windowed(2)
        .map { it.last() - it.first() }
        .groupBy { it }

    if (differences.keys.filterNot { it == 3 || it == 1 }.isNotEmpty()) throw RuntimeException("Only differences of 1 and 3 allowed")

    return differences[3]!!.size * differences[1]!!.size
}

fun solvePart1(input: List<Int>): Int {
    val device = input.maxOrNull()!! + 3

    return chainToOutput((input + device + 0).sorted())
}

fun main() {
    val input = readPuzzleInput("day10.txt").map { it.toInt() }
    println(solvePart1(input))
}
