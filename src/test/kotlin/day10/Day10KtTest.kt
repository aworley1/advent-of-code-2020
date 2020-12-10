package day10

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day10KtTest {
    @Test
    fun `should convert chain of adapters to part 1 output`() {
        //3 differences of 1 and 2 differences of 3
        val testChain = listOf(1,2,3,6,7,10)

        val exampleChain = listOf(0,1,4,5,6,7,10,11,12,15,16,19,22)

        assertThat(chainToOutput(testChain)).isEqualTo(6)
        assertThat(chainToOutput(exampleChain)).isEqualTo(35)
    }
}