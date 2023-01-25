import java.io.File

const val LOG = "log"
private val LOG_FILE = File("$FOLDER/$LOG.txt")

fun log() = if (LOG_FILE.isFile) LOG_FILE.forEachLine { println(it) } else println("No commits yet.")

fun addLog(commit: String, message: String) {
    val previous = File("$FOLDER/temp.txt")
    val commitStr = "commit $commit\n"
    val authorStr = "Author: ${getUserName()}\n"

    if (LOG_FILE.isFile) LOG_FILE.copyTo(previous, true)
    LOG_FILE.writeText(commitStr + authorStr + "$message\n\n")
    if (previous.isFile) {
        previous.forEachBlock(DEFAULT_BUFFER_SIZE) { buffer, bytesRead ->
            LOG_FILE.appendBytes(buffer.copyOfRange(0, bytesRead))
        }
        previous.delete()
    }
}

fun lastLogCommit() = if (LOG_FILE.isFile) LOG_FILE.bufferedReader().use { it.readLine().split(" ").last() } else ""