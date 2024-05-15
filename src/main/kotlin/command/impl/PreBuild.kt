package ltd.bauhinia.command.impl

import ltd.bauhinia.annotation.Command
import ltd.bauhinia.command.ICommand
import ltd.bauhinia.service.impl.KotlinTranslateService
import ltd.bauhinia.util.ifFalse
import ltd.bauhinia.util.logger
import java.io.File
import kotlin.system.exitProcess

@Command(
    aliases = ["prebuild", "pb"],
    args = ["<-type:str>", "<-input:str>", "[-output:str]"],
    description = "Translate chinese kotlin code to standard kotlin code"
)
class PreBuild : ICommand {
    private enum class Type(
        val str: String
    ) {
        FILE("file"),
        DIRECTORY("directory"),
        TEXT("text")
    }

    override fun execute(args: Map<String, String>) {
        val type = args["-type"]!!
        val input = args["-input"]!!
        val output: String? = args["-output"]


        val inputFile = File(input).also {
            it.exists().ifFalse {
                logger.info("Input file not found")
                exitProcess(0)
            }
        }

        if (type == Type.FILE.str || type == Type.DIRECTORY.str) {
            val outputFile = output?.let {
                File(it).also { file ->
                    file.createNewFile()
                }
            }!!

            if (type == Type.FILE.str) {
                KotlinTranslateService.translate(inputFile, outputFile)
            } else {
                KotlinTranslateService.translate(inputFile, outputFile) {
                    it.name.endsWith(KotlinTranslateService.SUFFIX)
                }
            }
        } else if (type == Type.TEXT.str) {
            println(KotlinTranslateService.translate(inputFile))
        }

    }
}