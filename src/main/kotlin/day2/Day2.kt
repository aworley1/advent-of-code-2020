package day2

import common.readPuzzleInput

fun solvePuzzlePart2(input: List<String>): Int {
    return input
        .map { policy(it) to password(it) }
        .count { (policy, password) -> policy.validate(password) }
}

private fun password(input: String) = input.split(":")[1].trim()

private fun String.hasCharAtPosition(letter: Char, position: Int) = this[position - 1] == letter

private fun policy(input: String): Policy {
    val policyString = input.split(":").first()
    val range = policyString.split(" ").first().split("-")
    val letter = policyString[policyString.lastIndex]
    return Policy(firstPosition = range[0].toInt(), secondPosition = range[1].toInt(), character = letter)
}

data class Policy(val character: Char, val firstPosition: Int, val secondPosition: Int) {
    fun validate(password: String): Boolean {
        return password.hasCharAtPosition(character, firstPosition) xor
                password.hasCharAtPosition(character, secondPosition)
    }
}

fun main() {
    println(solvePuzzlePart2(readPuzzleInput("day2.txt")))
}