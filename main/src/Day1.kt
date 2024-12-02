import kotlin.math.abs

fun main() {
    val leftColumn = mutableListOf<Int>()
    val rightColumn = mutableListOf<Int>()

    getLinesFromFile("day1.txt")
        .map { it.split("   ") }
        .map { (left, right) ->
            leftColumn.add(left.toInt())
            rightColumn.add(right.toInt())
        }

    partOne(leftColumn, rightColumn)
    partTwo(leftColumn, rightColumn)
}

private fun partOne(
    leftColumn: MutableList<Int>,
    rightColumn: MutableList<Int>
) {
    val leftHeap = leftColumn.heapify()
    val rightHeap = rightColumn.heapify()
    val distances = mutableListOf<Int>()

    while (leftHeap.isNotEmpty() && rightHeap.isNotEmpty()) {
        distances.add(abs(leftHeap.poll() - rightHeap.poll()))
    }

    println(distances.sum())
}

private fun partTwo(
    leftColumn: MutableList<Int>,
    rightColumn: MutableList<Int>
) {
    val rightFreq = rightColumn.groupingBy { it }.eachCount()
    val similarityScore = leftColumn.fold(0) { acc, value ->
        acc + (value * rightFreq.getOrDefault(value, 0))
    }
    println(similarityScore)
}