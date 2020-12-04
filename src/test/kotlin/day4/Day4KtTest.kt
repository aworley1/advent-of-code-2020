package day4

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import matchers.isFailure
import matchers.isSuccess
import org.junit.jupiter.api.Test


internal class Day4KtTest {
    @Test
    fun `should parse passports into maps`() {
        val input = listOf(
            "byr:value1 iyr:value2",
            "eyr:value3",
            "",
            "hgt:value4 hcl:value5"
        )

        val result = parseInput(input)

        assertThat(result[0]).isEqualTo(Passport(byr = "value1", iyr = "value2", eyr = "value3"))
        assertThat(result[1]).isEqualTo(Passport(hgt = "value4", hcl = "value5"))
    }

    @Test
    fun `should validate passport with all fields`() {
        val passport = Passport(
            byr = "value",
            iyr = "value",
            eyr = "value",
            hgt = "value",
            hcl = "value",
            ecl = "value",
            pid = "value",
            cid = "value"
        )

        assertThat(validateRequiredFields(passport)).isTrue()
    }

    @Test
    fun `should not validate passport with missing fields`() {
        val passport = Passport(
            hcl = "value",
            ecl = "value",
            pid = "value",
            cid = "value"
        )

        assertThat(validateRequiredFields(passport)).isFalse()
    }

    @Test
    fun `birth year between 1920-2002 inclusive is valid`() {
        assertThat(validateBirthYear(Passport(byr = "1920"))).isSuccess()
        assertThat(validateBirthYear(Passport(byr = "2002"))).isSuccess()
        assertThat(validateBirthYear(Passport(byr = "1919"))).isFailure()
        assertThat(validateBirthYear(Passport(byr = "2003"))).isFailure()
        assertThat(validateBirthYear(Passport(byr = "abc"))).isFailure()
    }

    @Test
    fun `issue year between 2010-2020 inclusive is valid`() {
        assertThat(validateIssueYear(Passport(iyr = "2010"))).isSuccess()
        assertThat(validateIssueYear(Passport(iyr = "2020"))).isSuccess()
        assertThat(validateIssueYear(Passport(iyr = "2009"))).isFailure()
        assertThat(validateIssueYear(Passport(iyr = "2021"))).isFailure()
        assertThat(validateIssueYear(Passport(iyr = "abc"))).isFailure()
    }


}