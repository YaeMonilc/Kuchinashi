package ltd.bauhinia.util

import ltd.bauhinia.annotation.Command
import ltd.bauhinia.command.ICommand
import java.util.StringJoiner

object CommandLine {
    private val commandsMap = mutableMapOf<Command, ICommand>()

    private fun initCommand() {
        forEachSubTypes(ICommand::class.java) {
            if (!it.isAnnotationPresent(Command::class.java)) {
                return@forEachSubTypes
            }

            commandsMap[it.getAnnotation(Command::class.java)] = it.getDeclaredConstructor().newInstance()
        }
    }

    private fun matchArguments(
        argsMap: Map<String, String>,
        command: Command,
    ): Boolean {
        command.args.forEach { arg ->
            var required = false

            if (arg.startsWith("<") && arg.endsWith(">")) {
                required = true
            }

            val argStr = arg.substring(1, arg.length - 1)
            val argPair = argStr.split(":")

            val name = argPair[0]
            val attr = argPair[1]

            if (required && !argsMap.containsKey(name)) {
                return false
            }

            if (argsMap.containsKey(name)) {
                if (attr == "num") {
                    if (!argsMap[name]!!.all { it.isDigit() }) {
                        return false
                    }
                } else if (attr == "str") {
                    if (argsMap[name]!!.isBlank()) {
                        return false
                    }
                }
            }

        }

        return true
    }

    fun run(args: Array<String>) {
        initCommand()

        val argsMap = mutableMapOf<String, String>()
        var index = 0

        while (index < args.size) {
            val item = args[index]

            if (item.startsWith("-") && index + 1 < args.size) {
                val nextItem = args[++index]

                if (!nextItem.startsWith("-")) {
                    argsMap[item] = nextItem
                    index++
                } else {
                    argsMap[item] = ""
                }
            }
        }

        if (!argsMap.containsKey("-mode")) {
            return
        }

        val matchCommand = commandsMap
            .filter { it.key.aliases.contains(argsMap["-mode"]) }

        if (matchCommand.isEmpty()) {
            commandsMap.forEach { (t, u) ->

                logger.info(StringBuilder().apply {
                    appendLine()
                    appendLine("-----${u::class.java.simpleName}-----")

                    appendLine("Aliases: ${
                        StringJoiner(", ").apply {
                            t.aliases.forEach { alias ->
                                add(alias)
                            }
                        }
                    }")
                    appendLine("Args: ${t.args.toList()}")
                    appendLine("Description: ${t.description}")
                    appendLine("-----${
                        StringBuilder().apply {
                            repeat(u::class.java.simpleName.length) {
                                append("-")
                            }
                        }
                    }-----")
                    appendLine()
                }.toString())
            }

            return
        }

        matchCommand.forEach { (command, iCommand) ->
            if (command.enable && matchArguments(argsMap, command)) {
                iCommand.execute(argsMap)
            }
        }
    }
}