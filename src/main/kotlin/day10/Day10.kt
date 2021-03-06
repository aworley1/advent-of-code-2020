package day10

import common.readPuzzleInput
import java.util.concurrent.atomic.AtomicLong

fun chainToOutput(chain: List<Int>): Int {
    val differences = chain.windowed(2)
        .map { it.last() - it.first() }
        .groupBy { it }

    if (differences.keys.filterNot { it == 3 || it == 1 }.isNotEmpty()) throw RuntimeException("Only differences of 1 and 3 allowed")

    return differences[3]!!.size * differences[1]!!.size
}

fun validNext(adapter: Int, adapters: List<Int>): List<Int> {
    return adapters.filter { it > adapter && it <= adapter + 3 }
}

fun buildValidNextAdapters(adapters: List<Int>): Map<Int, List<Int>> {
    return adapters.map { it to validNext(it, adapters) }.toMap()
}

fun solvePart1(input: List<Int>): Int {
    val device = input.maxOrNull()!! + 3

    return chainToOutput((input + device + 0).sorted())
}

fun solvePart2(adapters: List<Int>): Long {
    return numberOfCombinationsForEachNumber(adapters + 0)[0]!!
}

fun numberOfCombinationsForEachNumber(adapters: List<Int>): Map<Int, Long> {
    val mapOfCombinations = mutableMapOf<Int, Long>()
    val targetAdapter = adapters.maxOrNull()!!
    adapters.sortedDescending().forEach {
        val validNextAdapters = validNext(it, adapters)
        val combinations = if (it == targetAdapter) 1L
        else {
            validNextAdapters.map { mapOfCombinations[it]!! }.sum()
        }

        mapOfCombinations[it] = combinations
    }

    return mapOfCombinations
}

fun main() {
    val input = readPuzzleInput("day10.txt").map { it.toInt() }
    println(solvePart1(input))
    println(solvePart2(input))
}
