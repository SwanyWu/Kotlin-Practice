import java.io.File

const val CONFIG = "config"
private val CONFIG_FILE = File("$FOLDER/$CONFIG.txt")

fun config(userName: String) {
    val name = if (userName.isNotEmpty()) {
        CONFIG_FILE.writeText(userName)
        userName
    } else getUserName()

    println(if (name.isEmpty()) "Please, tell me who you are." else "The username is $name.")
}

fun getUserName() = if (CONFIG_FILE.isFile) CONFIG_FILE.readText() else ""