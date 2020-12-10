package day10

import java.lang.RuntimeException

fun chainToOutput(chain: List<Int>): Int {
    val differences = chain.windowed(2)
        .map { it.last() - it.first() }
        .groupBy { it }

    if (differences.keys.filterNot { it == 3 || it == 1 }.isNotEmpty()) throw RuntimeException("Only differences of 1 and 3 allowed")

    return differences[3]!!.size * differences[1]!!.size
}
