package day6

import common.readPuzzleInputString

fun part1(input: String): Int = distinctAnswersPerGroup(parse(input)).sumBy { it.count() }
fun part2(input: String): Int = commonAnswersPerGroup(parse(input)).sumBy { it.count() }

fun parse(input: String) =
    input.trim()
        .split("\n\n")
        .map { it.split("\n") }

fun distinctAnswersPerGroup(input: List<List<String>>): List<Set<Char>> =
    input.map { it.joinToString("").toSet() }

fun commonAnswersPerGroup(input: List<List<String>>): List<Set<Char>> =
    input.map {
        it.map { it.toList() }
    }.map { group ->
        val allChars = group.flatten().toSet()
        allChars.mapNotNull { char ->
            if (group.all { it.contains(char) }) char else null
        }.toSet()
    }

fun main() {
    println(part1(readPuzzleInputString("day6.txt")))
    println(part2(readPuzzleInputString("day6.txt")))
}
