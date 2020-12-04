package day4

import common.readPuzzleInputString

val MANDATORY_FIELDS = listOf("byr","iyr","eyr","hgt","hcl","ecl","pid")

fun solvePart1(input: String): Int = parseInput(input).count { validate(it) }

fun validate(passport: Map<String,String>) = passport.keys.containsAll(MANDATORY_FIELDS)

fun parseInput(input:String): List<Map<String, String>> {
    return input.split("\n\n")
        .map {
            it.split("[\n ]".toRegex())
                .mapNotNull {
                    if (it.isEmpty()) null else {
                        val split = it.split(":")
                        split[0] to split[1]
                    }
                }.toMap()
        }
}

fun main() {
    println(solvePart1(readPuzzleInputString("day4.txt")))
}

