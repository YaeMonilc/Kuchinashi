package ltd.bauhinia.command.impl

import ltd.bauhinia.annotation.Command
import ltd.bauhinia.command.ICommand
import ltd.bauhinia.service.impl.KotlinTranslateService
import ltd.bauhinia.util.ifFalse
import ltd.bauhinia.util.ifTrue
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
        TEXT("text");

        override fun toString(): String {
            return str
        }
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

        when(type) {
            Type.FILE.toString() ,Type.DIRECTORY.toString() -> {
                val outputFile = File(output!!).also {
                    it.isDirectory.ifTrue {
                        it.mkdirs()
                    }
                }

                when(type) {
                    Type.FILE.toString() -> KotlinTranslateService.translate(inputFile, outputFile)
                    Type.DIRECTORY.toString() -> KotlinTranslateService.translate(inputFile, outputFile) {
                        it.name.endsWith(KotlinTranslateService.SUFFIX[0]) || it.name.endsWith(KotlinTranslateService.SUFFIX[1])
                    }
                }
            }
            Type.TEXT.toString() -> {
                println(KotlinTranslateService.translate(inputFile))
            }
        }

    }
}