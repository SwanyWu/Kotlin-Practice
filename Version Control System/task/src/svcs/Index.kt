import java.io.File

const val ADD = "add"
private val INDEX = File("$FOLDER/index.txt")

fun addToIndex(fileName: String) = if (fileName.isNotEmpty()) addFile(fileName) else printIndex()

fun getIndexFiles(): List<File>? {
    return if (INDEX.isFile) {
        val list = INDEX.readLines().map { File(it) }.filter { it.isFile }
        list.ifEmpty { null }
    } else null
}

private fun addFile(fileName: String) {
    if (File(fileName).isFile) {
        if (INDEX.isFile && INDEX.readLines().contains(fileName)) {
            println("$fileName is already added.")
        } else {
            INDEX.appendText("$fileName\n")
            println("The file '$fileName' is tracked.")
        }
    } else println("Can't find '$fileName'.")
}

private fun printIndex() {
    if (INDEX.isFile) {
        println("Tracked files:")
        print(INDEX.readText())
    } else help(ADD)
}