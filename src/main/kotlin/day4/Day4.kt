package day4

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.natpryce.Failure
import com.natpryce.Result
import com.natpryce.Success
import com.natpryce.flatMap
import common.readPuzzleInput
import kotlin.reflect.full.memberProperties

val mapper = jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
val BIRTH_YEAR_RANGE = 1920..2002
val ISSUE_YEAR_RANGE = 2010..2020
val EXPIRY_YEAR_RANGE = 2020..2030
val HEIGHT_RANGE_INS = 59..76
val HEIGHT_RANGE_CMS = 150..193
val VALID_EYE_COLOURS = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

fun solvePart1(input: List<String>): Int = parseInput(input).count { validateRequiredFields(it) is Success }

fun solvePart2(input: List<String>): Int = parseInput(input).count { validatePassport(it) is Success }

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

fun validatePassport(passport: Passport) =
    validateRequiredFields(passport)
        .flatMap { validateBirthYear(it) }
        .flatMap { validateIssueYear(it) }
        .flatMap { validateExpiryYear(it) }
        .flatMap { validateHeight(it) }
        .flatMap { validateHairColour(it) }
        .flatMap { validateEyeColour(it) }
        .flatMap { validatePassportId(it) }

fun validateRequiredFields(passport: Passport): Result<Passport, String> {
    val isValid = passport::class.memberProperties
        .filterNot { it.name == "cid" }
        .all { it.getter.call(passport) != null }

    return if (isValid) Success(passport) else Failure("Not All Fields Present")
}

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

fun validateHeight(passport: Passport): Result<Passport, String> {
    if (passport.hgt?.endsWith("in") == true) {
        val number = passport.hgt.dropLast(2).toIntOrNull()
        if (HEIGHT_RANGE_INS.contains(number)) return Success(passport)
    }

    if (passport.hgt?.endsWith("cm") == true) {
        val number = passport.hgt.dropLast(2).toIntOrNull()
        if (HEIGHT_RANGE_CMS.contains(number)) return Success(passport)
    }

    return Failure("Invalid Height")
}

fun validateHairColour(passport: Passport) =
    if ("#[0-9a-f]{6}".toRegex().matches(passport.hcl)) Success(passport)
    else Failure("Invalid Hair Colour")

fun validateEyeColour(passport: Passport) =
    if (VALID_EYE_COLOURS.contains(passport.ecl)) Success(passport)
    else Failure("Invalid eye colour")

fun validatePassportId(passport: Passport) =
    if ("[0-9]{9}".toRegex().matches(passport.pid)) Success(passport)
    else Failure("Invalid Passport ID")

fun main() {
    println(solvePart1(readPuzzleInput("day4.txt")))
    println(solvePart2(readPuzzleInput("day4.txt")))
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

private fun validateYear(year: String?, range: IntRange) = year?.toIntOrNull()?.let {
    range.contains(it)
}

private fun Regex.matches(input: String?) = matches(input ?: "")