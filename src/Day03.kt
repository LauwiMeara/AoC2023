data class Adjacent(val offsetY: Int, val offsetX: Int)

fun main() {
    fun isAdjacentToSymbol(input: List<String>, rowIndex: Int, columnIndex: Int): Boolean {
        val adjacents = listOf(
            Adjacent(-1, -1),
            Adjacent(-1, 0),
            Adjacent(-1, 1),
            Adjacent(0, -1),
            Adjacent(0, 1),
            Adjacent(1, -1),
            Adjacent(1, 0),
            Adjacent(1, 1))

        for (adjacent in adjacents) {
            val character = input[rowIndex + adjacent.offsetY][columnIndex + adjacent.offsetX]
            if (!character.isLetterOrDigit() && character != '.') {
                return true
            }
        }
        return false
    }

    fun getStartXOfPartNumber(input: List<String>, y: Int, x: Int): Int {
        var offsetX = -1
        while (input[y][x + offsetX].isDigit()) {
            offsetX--
        }
        return x + offsetX + 1
    }

    fun getPartNumber(input: List<String>, y: Int, x: Int): Int {
        var partNumber = input[y][x].toString()
        var offsetX = 1
        while (input[y][x + offsetX].isDigit()) {
            partNumber += input[y][x + offsetX]
            offsetX++
        }
        return partNumber.toInt()
    }

    fun getGearRatio(input: List<String>, y: Int, x: Int): Int {
        val partNumbers: MutableList<Int> = mutableListOf()
        // Left
        if (input[y][x - 1].isDigit()) {
            val startX = getStartXOfPartNumber(input, y, x - 1)
            partNumbers.add(getPartNumber(input, y, startX))
        }
        // Right
        if (input[y][x + 1].isDigit()) {
            partNumbers.add(getPartNumber(input, y, x + 1))
        }
        // Top
        if (input[y - 1][x].isDigit()) {
            val startX = getStartXOfPartNumber(input, y - 1, x)
            partNumbers.add(getPartNumber(input, y - 1, startX))
        } else {
            if (input[y - 1][x - 1].isDigit()){
                val startX = getStartXOfPartNumber(input, y - 1, x)
                partNumbers.add(getPartNumber(input, y - 1, startX))
            }
            if (input[y - 1][x + 1].isDigit()) {
                partNumbers.add(getPartNumber(input, y - 1, x + 1))
            }
        }
        // Bottom
        if (input[y + 1][x].isDigit()) {
            val startX = getStartXOfPartNumber(input, y + 1, x)
            partNumbers.add(getPartNumber(input, y + 1, startX))
        } else {
            if (input[y + 1][x - 1].isDigit()){
                val startX = getStartXOfPartNumber(input, y + 1, x)
                partNumbers.add(getPartNumber(input, y + 1, startX))
            }
            if (input[y + 1][x + 1].isDigit()) {
                partNumbers.add(getPartNumber(input, y + 1, x + 1))
            }
        }
        return if (partNumbers.size == 2) partNumbers.reduce{ acc, partNumber -> acc * partNumber}
        else 0
    }

    fun part1(input: List<String>): Int {
        var sum = 0
        for (rowIndex in 1 until input.size - 1) {
            var isAdjacentToSymbol = false
            var digits = ""
            for (columnIndex in 1 until input[rowIndex].length) {
                val character = input[rowIndex][columnIndex]
                if (character.isDigit()) {
                    digits += character
                    if (!isAdjacentToSymbol) {
                        isAdjacentToSymbol = isAdjacentToSymbol(input, rowIndex, columnIndex)
                    }
                } else {
                    if (isAdjacentToSymbol) {
                        sum += digits.toInt()
                        isAdjacentToSymbol = false
                    }
                    digits = ""
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for (rowIndex in 1 until input.size - 1) {
            for (columnIndex in 1 until input[rowIndex].length) {
                if (input[rowIndex][columnIndex] == '*') {
                    sum += getGearRatio(input, rowIndex, columnIndex)
                }
            }
        }
        return sum
    }

    val input = readInputAsGridWithEmptyBorder("Day03", '.')
    part1(input).println()
    part2(input).println()
}
