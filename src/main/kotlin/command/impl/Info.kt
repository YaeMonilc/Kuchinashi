package ltd.bauhinia.command.impl

import ltd.bauhinia.annotation.Command
import ltd.bauhinia.command.ICommand
import ltd.bauhinia.util.logger

@Command(
    aliases = ["info", "help", "?"],
    description = "show Kuchinashi information about the current version",
)
class Info : ICommand {
    override fun execute(args: Map<String, String>) {
        logger.info("--------INFO--------")
        logger.info("|                  |")
        logger.info("|    Kuchinashi    |")
        logger.info("|                  |")
        logger.info("--------------------")
    }
}