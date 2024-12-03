import kotlin.math.abs

fun main() {
    getLinesFromFile("day2.txt")
        .map { it.split(" ").map { it.toInt() } }
        .also {
            partOne(it)
            partTwo(it)
        }
}

private fun partOne(
    reports: List<List<Int>>
) {
    reports.count(::isIncreasingOrDecreasing).also(::println)
}

private fun partTwo(
    reports: List<List<Int>>
) {
    reports.filter { report ->
        for (i in report.indices) {
            val modifiedReport = report.filterIndexed { index, _ -> index != i }
            if (isIncreasingOrDecreasing(modifiedReport)) {
                return@filter true
            }
        }

        return@filter false
    }.count().also(::println)
}

private fun isIncreasingOrDecreasing(report: List<Int>): Boolean {
    val isIncreasing = report.zipWithNext().all { (a, b) -> a <= b && (abs(a - b) in 1..3) }
    val isDecreasing = report.zipWithNext().all { (a, b) -> a >= b && (abs(a - b) in 1..3) }

    return isDecreasing || isIncreasing
}