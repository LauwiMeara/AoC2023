data class AlmanacMap(val destinations: Set<LongRange>, val sources: Set<LongRange>)
data class Almanac(val seeds: Set<Long>, val maps: List<AlmanacMap>)

fun main() {
    fun getAlmanacMap(mapping: String): AlmanacMap {
        val parsedMapping = mapping.split(System.lineSeparator())
            .drop(1)
            .map{it.split(" ")
                .map{number -> number.toLong()}}
        val destinations = mutableSetOf<LongRange>()
        val sources = mutableSetOf<LongRange>()
        for (line in parsedMapping) {
            destinations.add(line[0] until line[0] + line[2])
            sources.add(line[1] until line[1] + line[2])
        }
        return AlmanacMap(destinations, sources)
    }

    fun parseInput(input: List<String>): Almanac {
        val seeds = input.first().substringAfter("seeds: ").split(" ").map{it.toLong()}.toMutableSet()
        val maps = mutableListOf<AlmanacMap>()
        for (mapping in input.drop(1)) {
            maps.add(getAlmanacMap(mapping))
        }
        return Almanac(seeds, maps)
    }

    fun getLowestLocationNumber(input: Almanac): Long {
        var lowestLocationNumber = Long.MAX_VALUE
        for (seed in input.seeds) {
            var number = seed
            for (map in input.maps) {
                for ((i: Int, sourceRange: LongRange) in map.sources.withIndex()) {
                    if (sourceRange.contains(number)) {
                        number = map.destinations.elementAt(i).first + (number - sourceRange.first)
                        break
                    }
                }
            }
            if (number < lowestLocationNumber) {
                lowestLocationNumber = number
            }
        }
        return lowestLocationNumber
    }

//    fun getSeedRanges(seedsInput: Set<Long>): Set<LongRange> {
//        val seedRanges = mutableSetOf<LongRange>()
//        for ((i: Int, inputValue: Long) in seedsInput.withIndex()) {
//            if (i % 2 != 0) continue
//            seedRanges.add(inputValue until inputValue + seedsInput.elementAt(i + 1))
//        }
//        return seedRanges
//    }

    fun part1(input: Almanac): Long {
        return getLowestLocationNumber(input)
    }

//    fun part2(input: Almanac): Long {
//        val seedRanges = getSeedRanges(input.seeds)
//        return getLowestLocationNumber(input)
//    }

    val input = parseInput(readInputSplitByDelimiter("Day05", System.lineSeparator() + System.lineSeparator()))
    part1(input).println()
//    part2(input).println()
}
