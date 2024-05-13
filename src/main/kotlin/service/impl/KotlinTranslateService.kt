package ltd.bauhinia.service.impl

import ltd.bauhinia.enum.KotlinTokens
import ltd.bauhinia.service.IService
import ltd.bauhinia.util.forEachFiles
import ltd.bauhinia.util.ifFalse
import java.io.File

object KotlinTranslateService : IService {
    const val SUFFIX = ".kt"
    private val operators: List<Char> = listOf(
        ' ',
        '+',
        '-',
        '*',
        '/',
        ';',
        '&',
        '|',
        '<',
        '>',
        '=',
        '-',
        '(',
        ')',
        '{',
        '}',
        '[',
        ']',
        '.',
        '?',
        '!',
        '@',
        '\'',
        '\"',
        '\n',
        '\r',
        ';'
    )

    private fun translateToken(token:String, reserved: Boolean = false): String? {
        return if (!reserved)
            KotlinTokens.getByAliasName(token)?.originName
        else
            KotlinTokens.getByOriginName(token)?.aliasName
    }

    fun translate(code: String): String {
        val token: StringBuilder = StringBuilder()
        val translated: StringBuilder = StringBuilder()

        for (charCode in code.chars()) {
            val char = Character.toChars(charCode)[0]

            if (operators.contains(char)) {
                translated.append(translateToken(token.toString()) ?: token)
                token.clear()
                translated.append(char)
                continue
            }

            token.append(char)
        }

        return translated.toString()
    }

    fun translate(code: File): String {
        return translate(code.readText())
    }

    fun translate(code: File, to: File) {
        to.writeText(translate(code))
    }

    fun translate(dictionaryFile: File, toDictionary: File, beforeAction: (File) -> Boolean) {
        toDictionary.apply {
            exists().ifFalse {
                mkdirs()
            }
        }

        forEachFiles(dictionaryFile) {
            val toFile = File(toDictionary, it.path.replace(dictionaryFile.path, ""))

            toFile.parentFile.apply {
                exists().ifFalse {
                    mkdirs()
                }
            }

            if (beforeAction(it)) {
                translate(it, toFile.apply {
                    exists().ifFalse {
                        createNewFile()
                    }
                })
            } else {
                it.copyTo(toFile)
            }
        }
    }
}