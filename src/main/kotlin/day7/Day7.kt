package day7

fun parse(input: List<String>): List<Rule> {
    return input.map {
        val (colour, rest) = it.split(" bags contain")

        val colourCounts = if (rest.startsWith(" no other")) emptyList() else
            rest.split(",").map {
                println(it)
                val (num, col1, col2) = it.trim().split(" ")
                ColourCount("$col1 $col2", num.toInt())
            }

        Rule(colour, colourCounts)
    }
}

data class Rule(val colour: String, val canContain: List<ColourCount>)

data class ColourCount(val colour: String, val count: Int)