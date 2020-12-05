package day5

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import matchers.isFailure
import matchers.isSuccess
import org.junit.jupiter.api.Test

internal class Day5KtTest {
    @Test
    fun `should find row for examples`() {
        assertThat(findRow("FBFBBFF")).isEqualTo(44)
        assertThat(findRow("BFFFBBF")).isEqualTo(70)
        assertThat(findRow("FFFBBBF")).isEqualTo(14)
        assertThat(findRow("BBFFBBF")).isEqualTo(102)
    }
}