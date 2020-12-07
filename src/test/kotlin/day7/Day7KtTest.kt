package day7

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.containsOnly
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class Day7KtTest {
    @Test
    fun `should parse input 1`() {
        val testInput = listOf(
            "light red bags contain 1 bright white bag, 2 muted yellow bags.",
        )

        val result = parse(testInput)

        assertThat(result[0]).isEqualTo(
            Rule(
                "light red",
                listOf(
                    ColourCount("bright white", 1),
                    ColourCount("muted yellow", 2)
                )
            )
        )
    }

    @Test
    fun `should parse input 2`() {
        val testInput = listOf(
            "bright white bags contain 1 shiny gold bag.",
        )

        val result = parse(testInput)

        assertThat(result[0]).isEqualTo(
            Rule(
                "bright white",
                listOf(
                    ColourCount("shiny gold", 1),
                )
            )
        )
    }

    @Test
    fun `should parse input 3`() {
        val testInput = listOf(
            "dotted black bags contain no other bags.",
        )

        val result = parse(testInput)

        assertThat(result[0]).isEqualTo(
            Rule(
                "dotted black",
                emptyList()
            )
        )
    }

    @Test
    fun `should find bags which can hold this bag`() {
        val myBagColour = "blue"

        val rules = listOf(Rule("red", listOf(ColourCount("blue", 1))))

        val result = bagsWhichCanHoldMyBag(myBagColour, rules)

        assertThat(result).contains("red")
    }

    @Test
    fun `should find bags which can hold bags which can hold this bag`() {
        val myBagColour = "blue"

        val rules = listOf(
            Rule("red", listOf(ColourCount("blue", 1))),
            Rule("green", listOf(ColourCount("red", 1))),
            Rule("pink", listOf(ColourCount("green", 1))),
            Rule("purple", listOf(ColourCount("orange", 1)))
        )

        val result = bagsWhichCanHoldMyBag(myBagColour, rules)

        assertThat(result).containsOnly("red", "green", "pink")
    }
}
