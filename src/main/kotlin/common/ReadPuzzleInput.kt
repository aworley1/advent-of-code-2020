package common

import java.io.File

val DIRECTORY = System.getenv("HOME") + "/aoc-2020/"

fun readPuzzleInput(filename: String) = File(DIRECTORY + filename).readLines()