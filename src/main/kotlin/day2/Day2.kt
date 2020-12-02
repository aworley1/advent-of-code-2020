package day2

import common.readPuzzleInput

fun solvePuzzlePart1(input: List<String>): Int {
    return input.map { policy(it) to it.split(":")[1].trim() }
        .count { isPasswordValid(policy = it.first, password = it.second) }
}

private fun isPasswordValid(password: String, policy: Policy): Boolean {
    val count = password.count { it.toString() == policy.letter }

    return count <= policy.maxCount && count >= policy.minCount
}

private fun policy(input: String): Policy {
    val policyString = input.split(":").first()
    val range = policyString.split(" ").first().split("-")
    val letter = policyString[policyString.lastIndex]
    return Policy(minCount = range[0].toInt(), maxCount = range[1].toInt(), letter = letter.toString())
}

data class Policy(val letter: String, val minCount: Int, val maxCount: Int)

fun main() {
    println(solvePuzzlePart1(readPuzzleInput("day2.txt")))
//    println(solvePuzzlePart2(readPuzzleInput("day2.txt")))

//    println(
//        solvePuzzlePart1(
//            listOf(
//                "1-3 a: abcde",
//                "1-3 b: cdefg",
//                "2-9 c: ccccccccc"
//            )
//        )
//    )
}