fun main() {
    getLinesFromFile("day4.txt")
        .also {
            partOne(it)
            partTwo(it)
        }
}

private val directions = listOf(
    Pair(0, 1),
    Pair(0, -1),
    Pair(1, 0),
    Pair(-1, 0),
    Pair(1, 1),
    Pair(-1, -1),
    Pair(1, -1),
    Pair(-1, 1)
)

private fun partOne(board: List<String>) {
    var result = 0

    for (i in board.indices) {
        for (j in board[i].indices) {
            for (dir in directions) {
                if (hasTargetWord(board, i, j, dir.first, dir.second)) {
                    result++
                }
            }
        }
    }

    println(result)
}

private fun hasTargetWord(grid: List<String>, row: Int, col: Int, dRow: Int, dCol: Int): Boolean {
    val target = "XMAS"
    if (row + (target.length - 1) * dRow !in grid.indices ||
        col + (target.length - 1) * dCol !in grid[0].indices) {
        return false
    }

    return buildString {
        for (i in target.indices) {
            append(grid[row + i * dRow][col + i * dCol])
        }
    } == target
}

private fun partTwo(board: List<String>) {
    var result = 0
    for (row in 0..board.size - 3) {
        for (col in 0..board[0].length - 3) {
            if (isXmas(board, row, col)) {
                result++
            }
        }
    }

    println(result)
}

private fun isXmas(grid: List<String>, startRow: Int, startCol: Int): Boolean {
    if (startRow + 2 >= grid.size || startCol + 2 >= grid[0].length) return false

    val (topLeft, topRight) = grid[startRow][startCol] to grid[startRow][startCol + 2]
    val (bottomLeft, bottomRight) = grid[startRow + 2][startCol] to grid[startRow + 2][startCol + 2]
    val middle = grid[startRow + 1][startCol + 1]

    return "$topLeft$middle$bottomRight".isSequenceValid() && "$topRight$middle$bottomLeft".isSequenceValid()
}

private fun String.isSequenceValid(): Boolean =
    this == "MAS" || this == "SAM"
