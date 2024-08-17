fun main() {
    fun getNextSequence(previousSequence: MutableList<Int>): MutableList<Int> {
        val nextSequence = mutableListOf<Int>()
        for (i: Int in previousSequence.indices) {
            if (i == previousSequence.size - 1) break
            val firstNumber = previousSequence[i]
            val secondNumber = previousSequence[i + 1]
            val difference = secondNumber - firstNumber
            nextSequence.add(difference)
        }
        return nextSequence
    }

    fun part1(input: List<List<Int>>): Int {
        var totalValue = 0
        input.forEach { firstSequence ->
            val sequences = mutableListOf<MutableList<Int>>()
            sequences.add(firstSequence.toMutableList())

            // Get all sequences consisting of the differences between the numbers of the previous sequence
            var index = 0
            while (!sequences.last().all{ it == 0 }) {
                sequences.add(getNextSequence(sequences[index]))
                index++
            }

            // Get the next value of the first sequence
            val nextValue = sequences.map{it.last()}.reduce{acc, lastNumber -> acc + lastNumber}
            totalValue += nextValue
        }
        return totalValue
    }

    fun part2(input: List<List<Int>>): Int {
        var totalValue = 0
        input.forEach { firstSequence ->
            val sequences = mutableListOf<MutableList<Int>>()
            sequences.add(firstSequence.toMutableList())

            // Get all sequences consisting of the differences between the numbers of the previous sequence
            var index = 0
            while (!sequences.last().all { it == 0 }) {
                sequences.add(getNextSequence(sequences[index]))
                index++
            }

            // Get the previous value of the previous sequence
            while (index != 0) {
                val previousValueOfNextSequence = sequences[index].first()
                index--
                val firstValueOfPreviousSequence = sequences[index].first()
                val previousValueOfPreviousSequence = firstValueOfPreviousSequence - previousValueOfNextSequence
                sequences[index].add(0, previousValueOfPreviousSequence)
            }

            // Get the previous value of the first sequence
            totalValue += sequences.first().first()
        }
        return totalValue
    }

    val input = readInputAsStrings("Day09")
        .map{line -> line.split(" ")
            .map{it.toInt()}}
    part1(input).println()
    part2(input).println()
}
