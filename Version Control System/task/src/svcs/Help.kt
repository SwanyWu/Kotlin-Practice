private const val HELP = "--help"
private const val MAX_SPACE = 11 // largest command length in HELP_MAP + 3
private val HELP_MAP = mapOf(
    CONFIG to "Get and set a username.",
    ADD to "Add a file to the index.",
    LOG to "Show commit logs.",
    COMMIT to "Save changes.",
    CHECKOUT to "Restore a file."
)

fun help(command: String) {
    if (command.isEmpty() || command == HELP) {
        println("These are SVCS commands:")
        HELP_MAP.forEach { (command, description) ->
            println("$command${" ".repeat(MAX_SPACE - command.length)}$description")
        }
    } else {
        println(HELP_MAP.getOrDefault(command, "'$command' is not a SVCS command."))
    }
}