import kotlin.math.pow

data class Scratchcard(val winningNumbers: Set<Int>, val ownNumbers: Set<Int>)
data class CopiesPerScratchcard(var numberOfCopies: Int, val scratchcard: Scratchcard)

fun main() {
    fun parseInput(input: List<String>): List<Scratchcard> {
        val scratchcards = mutableListOf<Scratchcard>()
        for (line in input) {
            val winningNumbers = line.substringAfter(":")
                .substringBefore("|")
                .splitIgnoreEmpty(" ")
                .map{it.toInt()}
            val ownNumbers = line.substringAfter("|")
                .splitIgnoreEmpty(" ")
                .map{it.toInt()}
            scratchcards.add(Scratchcard(winningNumbers.toSet(), ownNumbers.toSet()))
        }
        return scratchcards
    }

    fun part1(input: List<Scratchcard>): Int {
        var sum = 0
        for (scratchcard in input) {
            val numberOfMatches = scratchcard.winningNumbers.intersect(scratchcard.ownNumbers).size
            if (numberOfMatches != 0) {
                val points = 2.0.pow(numberOfMatches - 1).toInt()
                sum += points
            }
        }
        return sum
    }

    fun part2(input: List<Scratchcard>): Int {
        val copiesPerScratchcard = mutableListOf<CopiesPerScratchcard>()
        for (scratchcard in input) {
            copiesPerScratchcard.add(CopiesPerScratchcard(1, scratchcard))
        }
        for ((i: Int, item: CopiesPerScratchcard) in copiesPerScratchcard.withIndex()) {
            val numberOfMatches = item.scratchcard.winningNumbers.intersect(item.scratchcard.ownNumbers).size
            for (cardNumber in i + 1..i + numberOfMatches) {
                copiesPerScratchcard[cardNumber].numberOfCopies += item.numberOfCopies
            }
        }
        return copiesPerScratchcard.map{it.numberOfCopies}.reduce{ acc, numberOfCopies -> acc + numberOfCopies}
    }

    val input = parseInput(readInputAsStrings("Day04"))
    part1(input).println()
    part2(input).println()
}
