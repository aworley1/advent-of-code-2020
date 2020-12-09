package day8

import common.readPuzzleInput
import day8.Op.*

fun main() {
    println(solvePart1String(readPuzzleInput("day8.txt")))
    println(solvePart2(readPuzzleInput("day8.txt")))
}

fun solvePart1String(input: List<String>) = solvePart1(parse(input))

fun solvePart1(instructions: List<Instruction>): Int? {
    var accumulator = 0
    var pointer = 0

    while (true) {
        val instruction = instructions[pointer]
        if (instruction.timesRun == 1) return null
        when (instruction.operation) {
            NOP -> pointer++
            ACC -> {
                accumulator += instruction.argument
                pointer++
            }
            JMP -> pointer += instruction.argument
        }
        instruction.timesRun++

        if (pointer == instructions.size) return accumulator
    }
}

fun solvePart2(input: List<String>): Int {
    val parsedInput = parse(input)
    val inputs = parsedInput.mapIndexed { outerIndex, _ ->
        parsedInput.mapIndexed { innerIndex, instruction ->
            if (innerIndex == outerIndex) instruction.flip() else instruction.copy()
        }
    }


    return inputs.mapNotNull {
        solvePart1(it)
    }.single()

}

fun parse(input: List<String>): List<Instruction> =
    input.map {
        val (op, arg) = it.split(" ")
        Instruction(Op.fromCode(op), arg.toInt())
    }

data class Instruction(val operation: Op, val argument: Int, var timesRun: Int = 0) {
    fun flip(): Instruction {
        return when (operation) {
            NOP -> copy(operation = JMP)
            ACC -> copy(operation = ACC)
            JMP -> copy(operation = NOP)
        }
    }
}

enum class Op(val code: String) {
    NOP("nop"), ACC("acc"), JMP("jmp");

    companion object {
        fun fromCode(code: String): Op = values().single { it.code == code }
    }
}
