package day6

import common.readPuzzleInputString

fun part1(input: String): Int = distinctAnswersPerGroup(parse(input)).sumBy { it.count() }

fun parse(input: String) =
    input.trim()
        .split("\n\n")
        .map { it.split("\n") }

fun distinctAnswersPerGroup(input: List<List<String>>): List<Set<Char>> =
    input.map { it.joinToString("").toSet() }

fun main() {
    println(part1(readPuzzleInputString("day6.txt")))
}