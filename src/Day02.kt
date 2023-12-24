data class Game(val id: Int, val cubes: List<Cube>)
data class Cube(val amount: Int, val color: CubeColor)
enum class CubeColor{BLUE, RED, GREEN}

fun main() {
    fun parseInput(input: List<String>): List<Game> {
        val parsedInput = mutableListOf<Game>()
        for (line in input) {
            val gameId = line.substringAfter("Game ").substringBefore(":").toInt()
            val cubes = line.substringAfter(": ").split("; ", ", ").map { cube ->
                    Cube(
                        cube.substringBefore(" ").toInt(),
                        when (cube.substringAfter(" ")) {
                            "blue" -> CubeColor.BLUE
                            "red" -> CubeColor.RED
                            "green" -> CubeColor.GREEN
                            else -> throw Exception("Cube color ${cube.substringAfter(" ")} not recognized")
                        }
                    )
                }
            parsedInput.add(Game(gameId, cubes))
        }
        return parsedInput
    }

    fun part1(input: List<Game>): Int {
        var sum = 0
        for (game in input) {
            if (game.cubes.any{it.color == CubeColor.RED && it.amount > 12 ||
                        it.color == CubeColor.GREEN && it.amount > 13 ||
                        it.color == CubeColor.BLUE && it.amount > 14}) continue
            else sum += game.id
        }
        return sum
    }

    fun part2(input: List<Game>): Int {
        var sum = 0
        for (game in input) {
            val maxRedCubes = game.cubes.filter{it.color == CubeColor.RED}.maxOf{it.amount}
            val maxGreenCubes = game.cubes.filter{it.color == CubeColor.GREEN}.maxOf{it.amount}
            val maxBlueCubes = game.cubes.filter{it.color == CubeColor.BLUE}.maxOf{it.amount}
            sum += (maxRedCubes * maxGreenCubes * maxBlueCubes)
        }
        return sum
    }

    val input = parseInput(readInputAsStrings("Day02"))
    part1(input).println()
    part2(input).println()
}
