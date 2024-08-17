fun main() {
    data class NodeInstruction(val left: String, val right: String)
    val LEFT = 'L'
    val RIGHT = 'R'

    fun parseNodeInstructions(input: List<String>): Map<String, NodeInstruction> {
        val nodeInstructions = mutableMapOf<String, NodeInstruction>()
        for (line in input) {
            val parsedLine = line.filter{it.isLetter()}.chunked(3)
            if (parsedLine.all{it.length != 3}) throw Exception("Incorrect elements in input. Elements should be three letters long")
            nodeInstructions[parsedLine[0]] = NodeInstruction(parsedLine[1], parsedLine[2])
        }
        return nodeInstructions
    }

    fun getNextNode(currentNode: String, nodeInstructions: Map<String, NodeInstruction>, leftRight: Char): String {
        if (nodeInstructions[currentNode] == null) throw Exception("Current node $currentNode not found")
        return when (leftRight) {
            LEFT -> nodeInstructions[currentNode]!!.left
            RIGHT -> nodeInstructions[currentNode]!!.right
            else -> throw Exception("Left right instruction $leftRight is incorrect")
        }
    }

    fun part1(input: List<String>): Int {
        var currentNode = "AAA"
        val endNode = "ZZZ"
        val leftRightInstruction = input.first()
        var leftRightInstructionIndex = 0
        val maxLeftRightInstructionIndex = leftRightInstruction.length - 1
        val nodeInstructions = parseNodeInstructions(input.drop(2))
        var steps = 0
        while (currentNode != endNode) {
            val leftRight = leftRightInstruction[leftRightInstructionIndex]
            currentNode = getNextNode(currentNode, nodeInstructions, leftRight)
            leftRightInstructionIndex = if (leftRightInstructionIndex == maxLeftRightInstructionIndex) 0 else leftRightInstructionIndex + 1
            steps++
        }
        return steps
    }

    fun part2(input: List<String>): Long {
        return 0
    }

    val input = readInputAsStrings("Day08")
    part1(input).println()
    part2(input).println()
}
