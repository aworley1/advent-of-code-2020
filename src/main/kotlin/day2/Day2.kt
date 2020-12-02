package day2

import common.readPuzzleInput

fun solvePuzzlePart2(input: List<String>): Int {
    return input.map { policy(it) to it.split(":")[1].trim() }
        .count { isPasswordValid(policy = it.first, password = it.second) }
}

private fun isPasswordValid(password: String, policy: Policy): Boolean {
    return password.containsLetterAtPosition(policy.letter, policy.firstPosition) xor
            password.containsLetterAtPosition(policy.letter, policy.secondPosition)
}

private fun String.containsLetterAtPosition(letter: Char, position: Int) = this[position - 1] == letter

private fun policy(input: String): Policy {
    val policyString = input.split(":").first()
    val range = policyString.split(" ").first().split("-")
    val letter = policyString[policyString.lastIndex]
    return Policy(firstPosition = range[0].toInt(), secondPosition = range[1].toInt(), letter = letter)
}

data class Policy(val letter: Char, val firstPosition: Int, val secondPosition: Int)

fun main() {
    println(solvePuzzlePart2(readPuzzleInput("day2.txt")))
}