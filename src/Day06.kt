data class RaceRecord(val time: Long, val distance: Long)

fun main() {
    fun getNumberOfPossibleRecordBeats(record: RaceRecord): Long {
        var numberOfPossibleRecordBeats = 0L
        var timeButtonPushed = 0L
        var reachedDistance = 0L
        while (reachedDistance <= record.distance) {
            timeButtonPushed++
            reachedDistance = (record.time - timeButtonPushed) * timeButtonPushed
        }
        while (reachedDistance > record.distance) {
            numberOfPossibleRecordBeats++
            timeButtonPushed++
            reachedDistance = (record.time - timeButtonPushed) * timeButtonPushed
        }
        return numberOfPossibleRecordBeats
    }

    fun part1(input: List<List<String>>): Long {
        val records = input[0].zip(input[1]) {time, distance -> RaceRecord(time.toLong(), distance.toLong())}
        return records.map{getNumberOfPossibleRecordBeats(it)}.reduce{acc, number -> acc * number}
    }

    fun part2(input: List<List<String>>): Long {
        val record = input
            .map{it.reduce{acc, digits -> acc + digits}}
            .zipWithNext{time, distance -> RaceRecord(time.toLong(), distance.toLong())}
            .single()
        return getNumberOfPossibleRecordBeats(record)
    }

    val input = readInputAsStrings("Day06")
        .map{it.splitIgnoreEmpty(" ")}
        .map{it.drop(1)}
    if (input.size != 2) throw Exception("Incorrect number of lines in input. Input should only contain times and distances")
    part1(input).println()
    part2(input).println()
}
