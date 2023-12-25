@file:Suppress("unused")

import java.io.File

/**
 * Reads from the given input txt file as one string.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readText()

/**
 * Reads from the given input txt file as strings split by the given delimiter.
 */
fun readInputSplitByDelimiter(name: String, delimiter: String) = File("src", "$name.txt")
    .readText()
    .split(delimiter)

/**
 * Reads lines from the given input txt file as strings.
 */
fun readInputAsStrings(name: String) = File("src", "$name.txt")
    .readLines()

/**
 * Reads lines from the given input txt file as integers.
 */
fun readInputAsInts(name: String) = File("src", "$name.txt")
    .readLines()
    .map{it.toInt()}

/**
 * Reads input as a grid and adds a border with the given 'empty' character.
 */
fun readInputAsGridWithEmptyBorder(name: String, emptyCharacter: Char): List<String> {
    val input = readInputAsStrings(name)
    val emptyRow = emptyCharacter.toString().repeat(input.first().length)
    val borderedInput = mutableListOf(emptyRow)
    for (line in input) {
        val borderedLine = "$emptyCharacter$line$emptyCharacter"
        borderedInput.add(borderedLine)
    }
    borderedInput.add(emptyRow)
    return borderedInput
}

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)
