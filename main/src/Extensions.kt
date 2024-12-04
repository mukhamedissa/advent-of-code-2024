import java.util.PriorityQueue

fun getLinesFromFile(name: String): List<String> =
    object {}.javaClass.getResourceAsStream(name)?.bufferedReader()?.readLines() ?: emptyList()

fun getTextFromFile(name: String): String? =
    object {}.javaClass.getResourceAsStream(name)?.bufferedReader()?.readText()

fun ArrayList<Int>.addToPosition(
    position: Int = -1,
    value: Int
) {
    if (position == -1) {
        add(value)
    } else {
        add(position, value)
    }
}

fun Boolean.toInt() = if (this) 1 else 0

fun MutableList<Int>.heapify(): PriorityQueue<Int> =
    PriorityQueue<Int>().apply {
        addAll(this@heapify)
    }

fun String.remove(vararg regex: Regex): String {
    var str = this
    for (r in regex) {
        str = str.replace(r, "")
    }
    return str
}
