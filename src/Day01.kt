fun main() {
    fun getCalibrationValueUsingDigits(i: Int, line: String): Int {
        val firstDigit = line.firstOrNull { it.isDigit() }?.toString()
        val lastDigit = line.reversed().firstOrNull {it.isDigit() }?.toString()
        if (firstDigit.isNullOrEmpty() || lastDigit.isNullOrEmpty()) throw Exception("No digit found for line $i")
        return (firstDigit + lastDigit).toInt()
    }

    fun getCalibrationValueUsingDigitsAndSpelledNumbers(i: Int, line: String): Int {
        val numbers = mapOf(
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9"
        )

        var firstDigit = line.findAnyOf(numbers.keys + numbers.values + "0")?.second
        var lastDigit = line.findLastAnyOf(numbers.keys + numbers.values + "0")?.second
        if (firstDigit.isNullOrEmpty() || lastDigit.isNullOrEmpty()) throw Exception("No digit found for line $i")
        if (numbers.keys.contains(firstDigit)) firstDigit = numbers[firstDigit]
        if (numbers.keys.contains(lastDigit)) lastDigit = numbers[lastDigit]
        return (firstDigit + lastDigit).toInt()
    }

    fun part1(input: List<String>): Int {
        var sum = 0
        for ((i: Int, line: String) in input.withIndex()) {
            sum += getCalibrationValueUsingDigits(i, line)
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for ((i: Int, line: String) in input.withIndex()) {
            sum += getCalibrationValueUsingDigitsAndSpelledNumbers(i, line)
        }
        return sum
    }

    val input = readInputAsStrings("Day01")
    part1(input).println()
    part2(input).println()
}
