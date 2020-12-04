package day4

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.natpryce.Failure
import com.natpryce.Success
import common.readPuzzleInput
import kotlin.reflect.full.memberProperties

val mapper = jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
val BIRTH_YEAR_RANGE = 1920..2002
val ISSUE_YEAR_RANGE = 2010..2020
val EXPIRY_YEAR_RANGE = 2020..2030

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

fun validateBirthYear(passport: Passport) =
    if (validateYear(passport.byr, BIRTH_YEAR_RANGE) == true)
        Success(passport)
    else
        Failure("Invalid Issue Year")

fun validateIssueYear(passport: Passport) =
    if (validateYear(passport.iyr, ISSUE_YEAR_RANGE) == true)
        Success(passport)
    else
        Failure("Invalid Issue Year")

fun validateExpiryYear(passport: Passport) =
    if (validateYear(passport.eyr, EXPIRY_YEAR_RANGE) == true)
        Success(passport)
    else
        Failure("Invalid Expiry Year")

fun validatePassportId(passport: Passport) =
    if ("[0-9]{9}".toRegex().matches(passport.pid ?: "")) Success(passport)
    else Failure("Invalid Passport ID")

private fun validateYear(year: String?, range: IntRange) = year?.toIntOrNull()?.let {
    range.contains(it)
}

fun main() {
    println(solvePart1(readPuzzleInput("day4.txt")))
}

