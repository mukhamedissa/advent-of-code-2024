fun main() {
    getTextFromFile("day3.txt")?.let {
        partOne(it)
        partTwo(it)
    }
}

private fun partOne(instructions: String) {
    val regex = Regex("""mul\(\d+,\d+\)""")
    val digitRegex = Regex("""\d+""")
    regex.findAll(instructions)
        .map {
            digitRegex.findAll(it.value).map { it.value.toInt() }.toList()
        }.fold(0) { acc, numbers ->
            acc + numbers[0] * numbers[1]
        }.also(::println)
}

private fun partTwo(instructions: String) {
    val regex = Regex("""mul\(\d+,\d+\)""")
    val digitRegex = Regex("""\d+""")

    regex.findAll(
        instructions.remove(Regex("""don't\(\).*?(?=do\(\)|$)""", RegexOption.DOT_MATCHES_ALL))
    ).map {
        digitRegex.findAll(it.value).map { it.value.toInt() }.toList()
    }.fold(0) { acc, numbers ->
        acc + numbers[0] * numbers[1]
    }.also(::println)
}