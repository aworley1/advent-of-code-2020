package day4

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.natpryce.Failure
import com.natpryce.Result
import com.natpryce.Success
import common.readPuzzleInput
import kotlin.reflect.full.memberProperties

val mapper = jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
val YEAR_RANGE = 1920..2002

fun solvePart1(input: List<String>): Int = parseInput(input).count { validateRequiredFields(it) }

fun validateRequiredFields(passport: Passport) = passport::class.memberProperties
    .filterNot { it.name == "cid" }
    .all { it.getter.call(passport) != null }

fun parseInput(input: List<String>): List<Passport> {
    return input.joinToString("|").split("||")
        .map { passport ->
            passport.replace("|", " ")
                .split(" ")
                .map { field ->
                    field.split(":").let { (key, value) -> key to value }
                }.toMap()
        }
        .map { mapper.convertValue(it, Passport::class.java) }
}

data class Passport(
    val byr: String? = null,
    val iyr: String? = null,
    val eyr: String? = null,
    val hgt: String? = null,
    val hcl: String? = null,
    val ecl: String? = null,
    val pid: String? = null,
    val cid: String? = null,
)

fun validateBirthYear(passport: Passport): Result<Passport, String> {
    val validYear = passport.byr?.toIntOrNull()?.let {
        YEAR_RANGE.contains(it)
    }

    return if (validYear == true) Success(passport) else Failure("Invalid Birth Year")
}

fun main() {
    println(solvePart1(readPuzzleInput("day4.txt")))
}

