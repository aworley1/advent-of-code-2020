package day7

import common.readPuzzleInput

fun main() {
    println(solvePart1(readPuzzleInput("day7.txt")))
    println(solvePart2(readPuzzleInput("day7.txt")))
}

fun solvePart1(input: List<String>): Int = bagsWhichCanHoldMyBag("shiny gold", parse(input)).size

fun solvePart2(input: List<String>): Int = myBagContains("shiny gold", parse(input)).sumBy { it.count }

fun bagsWhichCanHoldMyBag(colour: String, rules: List<Rule>): Set<String> {
    val holdBagDirectly = rules
        .filter { it.canContain.any { it.colour == colour } }
        .map { it.colour }
        .toSet()

    val holdBagIndirectly = holdBagDirectly.map { bagsWhichCanHoldMyBag(it, rules) }.flatten().toSet()

    return holdBagDirectly + holdBagIndirectly
}

fun myBagContains(colour: String, rules: List<Rule>): List<ColourCount> {
    val directlyInBag = rules.single { it.colour == colour }.canContain
    return directlyInBag + directlyInBag.flatMap { findDescendents(it, rules) }
}

private fun findDescendents(colourCount: ColourCount, rules: List<Rule>): List<ColourCount> {
    val directDescendents = rules.filter { it.colour == colourCount.colour }
        .flatMap { it.canContain.map { it.copy(count = it.count * colourCount.count) } }
    return directDescendents + directDescendents.flatMap { findDescendents(it, rules) }
}

fun parse(input: List<String>): List<Rule> {
    return input.map {
        val (colour, rest) = it.split(" bags contain")

        val colourCounts = if (rest.startsWith(" no other")) emptyList() else
            rest.split(",").map {
                val (num, col1, col2) = it.trim().split(" ")
                ColourCount("$col1 $col2", num.toInt())
            }

        Rule(colour, colourCounts)
    }
}

data class Rule(val colour: String, val canContain: List<ColourCount>)

data class ColourCount(val colour: String, val count: Int)