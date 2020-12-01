package day1

import common.readPuzzleInput

fun solvePuzzlePart1(input: List<String>): List<Int> {
    val intInput = input.map { it.toInt() }

    return intInput.flatMap { outer ->
        intInput.map { inner ->
            inner to outer
        }
    }.filter { it.first + it.second == 2020 }
        .map { it.first * it.second }
}

fun solvePuzzlePart2(input: List<String>): List<Int> {
    val intInput = input.map { it.toInt() }

    return intInput.flatMap { veryOuter ->
        intInput.flatMap { inner ->
            intInput.map { veryInner ->
                Triple(veryInner, inner, veryOuter)
            }
        }
    }.filter { it.first + it.second + it.third == 2020 }
        .map { it.first * it.second * it.third }
}

fun main() {
    println(solvePuzzlePart1(readPuzzleInput("day1.txt")))
    println(solvePuzzlePart2(readPuzzleInput("day1.txt")))

    println(
        solvePuzzlePart1(
            listOf(
                "1721",
                "979",
                "366",
                "299",
                "675",
                "1456",
            )
        )
    )
}