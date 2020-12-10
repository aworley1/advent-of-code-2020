package day9

import common.readPuzzleInput
import kotlin.system.measureTimeMillis

fun main() {
    val part1Answer = solvePart1(readPuzzleInput("day9.txt").map { it.toLong() }, 25)
    println(part1Answer)
    println(
        measureTimeMillis {
            println(solvePart2(readPuzzleInput("day9.txt").map { it.toLong() }, part1Answer))
        }
    )
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

fun solvePart2(input: List<Long>, target: Long): Long {
    (2..input.size).asSequence()
        .flatMap {
            input.windowed(it)
        }.forEach {
            if (it.sum() == target) return it.minOrNull()!! + it.maxOrNull()!!
        }

    throw RuntimeException("Not found")
}