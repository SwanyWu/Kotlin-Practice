import java.io.File

const val FOLDER = "vcs"

fun main(args: Array<String>) {
    val command = args.getOrNull(0) ?: ""
    val secondArgument = args.getOrNull(1) ?: ""
    val commands = listOf(CONFIG, ADD, COMMIT, LOG, CHECKOUT)

    if (!File(FOLDER).isDirectory && commands.contains(command)) File(FOLDER).mkdir()
    when (command) {
        CONFIG -> config(secondArgument)
        ADD -> addToIndex(secondArgument)
        COMMIT -> commit(secondArgument)
        LOG -> log()
        CHECKOUT -> checkout(secondArgument)
        CHECKOUT -> print("Restore a file.")
        else -> help(command)
    }
}

/*
package svcs

fun main(args: Array<String>) {
    val help = """
                These are SVCS commands:
                config     Get and set a username.
                add        Add a file to the index.
                log        Show commit logs.
                commit     Save changes.
                checkout   Restore a file.""".trimIndent()
                
    val config = "Get and set a username."
    val add = "Add a file to the index."
    val log = "Show commit logs."
    val commit = "Save changes."
    val checkout = "Restore a file."
    
    when (args.firstOrNull()) {
        "--help" -> println(help)
        null -> println(help)
        "config" -> println(config)
        "add" -> println(add)
        "log" -> println(log)
        "commit" -> println(commit)
        "checkout" -> println(checkout)
        else -> println("'${args.first()}' is not a SVCS command.")
    }
}
*/


