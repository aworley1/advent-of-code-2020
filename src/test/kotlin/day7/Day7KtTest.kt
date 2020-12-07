package day7

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.containsOnly
import assertk.assertions.hasSize
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

    @Test
    fun `should solve part 2 example`() {
        val rules = listOf(
            Rule("shiny gold", listOf(ColourCount("dark red", 2))),
            Rule("dark red", listOf(ColourCount("dark orange", 2))),
            Rule("dark orange", listOf(ColourCount("dark yellow", 2))),
            Rule("dark yellow", listOf(ColourCount("dark green", 2))),
            Rule("dark green", listOf(ColourCount("dark blue", 2))),
            Rule("dark blue", listOf(ColourCount("dark violet", 2))),
            Rule("dark violet", emptyList()),
        )

        val result = myBagContains("shiny gold", rules).sumBy { it.count }

        assertThat(result).isEqualTo(126)
    }

    @Test
    fun `should find the bags directly in my bag`() {
        val rules = listOf(
            Rule("red", listOf(ColourCount("yellow", 2), ColourCount("blue", 2))),
            Rule("blue", emptyList()),
            Rule("green", listOf(ColourCount("pink", 2), ColourCount("orange", 2))),
        )

        val result = myBagContains("red", rules)

        assertThat(result).containsOnly(ColourCount("yellow", 2), ColourCount("blue", 2))
    }

    @Test
    fun `should find children of the bags directly in my bag`() {
        val rules = listOf(
            Rule("red", listOf(ColourCount("yellow", 2), ColourCount("blue", 2))),
            Rule("yellow", listOf(ColourCount("mauve", 2))),
            Rule("blue", emptyList())
        )

        val result = myBagContains("red", rules)

        assertThat(result).containsOnly(
            ColourCount("yellow", 2),
            ColourCount("blue", 2),
            ColourCount("mauve", 4),
        )
    }

    @Test
    fun `should find children of children of the bags directly in my bag`() {
        val rules = listOf(
            Rule("red", listOf(ColourCount("yellow", 2))),
            Rule("yellow", listOf(ColourCount("mauve", 2))),
            Rule("mauve", listOf(ColourCount("emerald", 2)))
        )

        val result = myBagContains("red", rules)

        assertThat(result).containsOnly(
            ColourCount("yellow", 2),
            ColourCount("mauve", 4),
            ColourCount("emerald", 8),
        )
    }
}
