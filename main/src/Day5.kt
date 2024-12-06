private typealias Rule = Pair<Int, Int>
private typealias Update = List<Int>

private class UpdateComparator(
    private val rules: Set<Rule>
) : Comparator<Int> {
    override fun compare(a: Int?, b: Int?): Int =
        when {
            rules.contains(a to b) -> -1
            rules.contains(b to a) -> 1
            else -> 0
        }
}

private fun partOne(
    rules: List<Rule>,
    updates: List<Update>
) {
    val comparator = UpdateComparator(rules.toSet())
    updates.asSequence()
        .filter { update -> update == update.sortedWith(comparator) }
        .sumOf { update -> update[update.size / 2] }
        .also(::println)
}

private fun partTwo(
    rules: List<Rule>,
    updates: List<Update>
) {
    val comparator = UpdateComparator(rules.toSet())
    updates.asSequence()
        .filter { update -> update != update.sortedWith(comparator) }
        .fold(0) { acc, update -> acc + update.sortedWith(comparator)[update.size / 2] }
        .also(::println)
}

fun main() {
    getTextFromFile("day5.txt")?.let { input ->
        input.split("\r\n\r\n").let { (rulesBlock, updatesBlock) ->
            val rules = rulesBlock.lines()
                .map { it.split("|") }
                .map { it[0].toInt() to it[1].toInt() }

            val updates = updatesBlock.lines()
                .map { it.split(",").map(String::toInt) }

            partOne(rules, updates)
            partTwo(rules, updates)
        }
    }
}