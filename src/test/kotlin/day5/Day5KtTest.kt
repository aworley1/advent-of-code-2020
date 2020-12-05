package day5

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day5KtTest {
    @Test
    fun `should find row for examples`() {
        assertThat(findRow("FBFBBFF")).isEqualTo(44)
        assertThat(findRow("BFFFBBF")).isEqualTo(70)
        assertThat(findRow("FFFBBBF")).isEqualTo(14)
        assertThat(findRow("BBFFBBF")).isEqualTo(102)
    }

    @Test
    fun `should find column for examples`() {
        assertThat(findColumn("RLR")).isEqualTo(5)
        assertThat(findColumn("RRR")).isEqualTo(7)
        assertThat(findColumn("RLL")).isEqualTo(4)
    }

    @Test
    fun `should find seatId for examples`() {
        assertThat(findSeatId("BFFFBBFRRR")).isEqualTo(567)
        assertThat(findSeatId("FFFBBBFRRR")).isEqualTo(119)
        assertThat(findSeatId("BBFFBBFRLL")).isEqualTo(820)
    }
}