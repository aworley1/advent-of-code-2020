package day5

fun findRow(rowInput: String): Int {
    val startRange = (0..127).toList()

    return rowInput.fold(startRange) { acc, op ->
        if (op == 'F') acc.take(acc.size / 2)
        else acc.takeLast(acc.size / 2)
    }.single()

}
