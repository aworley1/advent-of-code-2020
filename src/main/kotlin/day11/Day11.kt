package day11

fun parse(input: List<String>): List<Space> {
    return input.flatMapIndexed { y, row ->
        row.mapIndexed { x, space ->
            Space(x, y, State.from(space))
        }
    }
}

data class Space(val x: Int, val y: Int, val state: State)

enum class State(val code: Char) {
    EMPTY('L'), OCCUPIED('#'), FLOOR('.');

    companion object {
        fun from(char: Char): State = values().singleOrNull { it.code == char }
            ?: throw RuntimeException("Invalid code")
    }
}