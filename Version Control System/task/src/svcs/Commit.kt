import java.io.File
import java.security.MessageDigest

const val COMMIT = "commit"
const val CHECKOUT = "checkout"
private const val COMMIT_FOLDER = "$FOLDER/commits"

fun commit(message: String) {
    var response = if (message.isEmpty()) "Message was not passed." else "Nothing to commit."

    if (message.isNotEmpty()) {
        getIndexFiles()?.let { files ->
            val hash = hashFiles(files)

            if (hash != lastLogCommit()) {
                addLog(hash, message)
                copyFiles(files, "$COMMIT_FOLDER/$hash")
                response = "Changes are committed."
            }
        }
    }
    println(response)
}

private fun hashFiles(files: List<File>): String {
    val hash: MessageDigest = MessageDigest.getInstance("SHA-256")

    files.forEach { file ->
        hash.update(file.name.toByteArray())
        file.forEachBlock(DEFAULT_BUFFER_SIZE) { buffer, bytesRead -> hash.update(buffer, 0, bytesRead) }
    }
    return hash.digest().joinToString("") { String.format("%02x", it) }
}

private fun copyFiles(files: List<File>, directory: String) {
    if (!File(directory).isDirectory) {
        files.forEach { file ->
            file.copyTo(File("$directory/$file"))
        }
    }
}

fun checkout(hash: String) = println(if (hash.isEmpty()) "Commit id was not passed." else revertToCommit(hash))

fun revertToCommit(hash: String): String {
    val directory = File("$COMMIT_FOLDER/$hash")

    return if (!directory.isDirectory) "Commit does not exist." else {
        directory.copyRecursively(File("./"), true)
        "Switched to commit $hash."
    }
}