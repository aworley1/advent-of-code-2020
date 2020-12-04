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

        assertThat(validateRequiredFields(passport)).isSuccess()
    }

    @Test
    fun `should not validate passport with missing fields`() {
        val passport = Passport(
            hcl = "value",
            ecl = "value",
            pid = "value",
            cid = "value"
        )

        assertThat(validateRequiredFields(passport)).isFailure()
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

    @Test
    fun `expiry year between 2020-2030 inclusive is valid`() {
        assertThat(validateExpiryYear(Passport(eyr = "2020"))).isSuccess()
        assertThat(validateExpiryYear(Passport(eyr = "2030"))).isSuccess()
        assertThat(validateExpiryYear(Passport(eyr = "2019"))).isFailure()
        assertThat(validateExpiryYear(Passport(eyr = "2031"))).isFailure()
        assertThat(validateExpiryYear(Passport(eyr = "abc"))).isFailure()
    }

    @Test
    fun `passport id should be a 9 digit number`() {
        assertThat(validatePassportId(Passport(pid="012345678"))).isSuccess()
        assertThat(validatePassportId(Passport(pid="0000000000"))).isFailure()
        assertThat(validatePassportId(Passport(pid="00000000"))).isFailure()
        assertThat(validatePassportId(Passport(pid="abcdefghi"))).isFailure()
    }

    @Test
    fun `eye colour should be an allowed value`() {
        assertThat(validateEyeColour(Passport(ecl="amb"))).isSuccess()
        assertThat(validateEyeColour(Passport(ecl="blu"))).isSuccess()
        assertThat(validateEyeColour(Passport(ecl="brn"))).isSuccess()
        assertThat(validateEyeColour(Passport(ecl="gry"))).isSuccess()
        assertThat(validateEyeColour(Passport(ecl="grn"))).isSuccess()
        assertThat(validateEyeColour(Passport(ecl="hzl"))).isSuccess()
        assertThat(validateEyeColour(Passport(ecl="oth"))).isSuccess()
        assertThat(validateEyeColour(Passport(ecl="somethingElse"))).isFailure()
    }

    @Test
    fun `hair colour should be a valid value`() {
        assertThat(validateHairColour(Passport(hcl = "#123abc"))).isSuccess()
        assertThat(validateHairColour(Passport(hcl = "#123abz"))).isFailure()
        assertThat(validateHairColour(Passport(hcl = "123abz"))).isFailure()
    }

    @Test
    fun `height should be valid`() {
        assertThat(validateHeight(Passport(hgt = "150cm"))).isSuccess()
        assertThat(validateHeight(Passport(hgt = "193cm"))).isSuccess()
        assertThat(validateHeight(Passport(hgt = "59in"))).isSuccess()
        assertThat(validateHeight(Passport(hgt = "76in"))).isSuccess()
        assertThat(validateHeight(Passport(hgt = "149cm"))).isFailure()
        assertThat(validateHeight(Passport(hgt = "194cm"))).isFailure()
        assertThat(validateHeight(Passport(hgt = "58in"))).isFailure()
        assertThat(validateHeight(Passport(hgt = "77in"))).isFailure()
        assertThat(validateHeight(Passport(hgt = "190"))).isFailure()
    }
}