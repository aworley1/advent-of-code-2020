package day8

import common.readPuzzleInput
import day8.Op.*

fun main() {
    println(solvePart1(readPuzzleInput("day8.txt")))
}

fun solvePart1(input: List<String>): Int {
    val instructions = parse(input)

    var accumulator = 0
    var pointer = 0

    while (true) {
        val instruction = instructions[pointer]
        if (instruction.timesRun != 0) return accumulator
        when (instruction.operation) {
            NOP -> pointer++
            ACC -> {
                accumulator += instruction.argument
                pointer++
            }
            JMP -> pointer += instruction.argument
        }
        instruction.timesRun++
    }
}

fun parse(input: List<String>): List<Instruction> =
    input.map {
        val (op, arg) = it.split(" ")
        Instruction(Op.fromCode(op), arg.toInt())
    }

data class Instruction(val operation: Op, val argument: Int, var timesRun: Int = 0)

enum class Op(val code: String) {
    NOP("nop"), ACC("acc"), JMP("jmp");

    companion object {
        fun fromCode(code: String): Op = values().single { it.code == code }
    }
}