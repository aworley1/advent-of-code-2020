package day4

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test

internal class Day4KtTest {
    @Test
    fun `should parse passports into maps`() {
        val input =
            "field1:value1 field2:value2\n" +
                    "field3:value3\n" +
                    "\n" +
                    "field4:value4 field5:value5\n"

        val result = parseInput(input)

        assertThat(result[0]).isEqualTo(mapOf("field1" to "value1", "field2" to "value2", "field3" to "value3"))
        assertThat(result[1]).isEqualTo(mapOf("field4" to "value4", "field5" to "value5"))
    }

    @Test
    fun `should validate passport with all fields`() {
        val passport = mapOf(
            "byr" to "value",
            "iyr" to "value",
            "eyr" to "value",
            "hgt" to "value",
            "hcl" to "value",
            "ecl" to "value",
            "pid" to "value",
            "cid" to "value"
        )

        assertThat(validate(passport)).isTrue()
    }

    @Test
    fun `should not validate passport with missing fields`() {
        val passport = mapOf(
            "hcl" to "value",
            "ecl" to "value",
            "pid" to "value",
            "cid" to "value"
        )

        assertThat(validate(passport)).isFalse()
    }
}