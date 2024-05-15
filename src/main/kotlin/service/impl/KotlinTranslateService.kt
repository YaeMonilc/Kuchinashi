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

    fun translate(code: String, reserved: Boolean = false): String {
        val token: StringBuilder = StringBuilder()
        val translated: StringBuilder = StringBuilder()

        for (charCode in code.chars()) {
            val char = Character.toChars(charCode)[0]

            if (operators.contains(char)) {
                translated.append(translateToken(token.toString(), reserved) ?: token)
                token.clear()
                translated.append(char)
                continue
            }

            token.append(char)
        }

        return translated.toString()
    }

    fun translate(code: File, reserved: Boolean = false): String {
        return translate(code.readText(), reserved)
    }

    fun translate(code: File, to: File, reserved: Boolean = false) {
        to.writeText(translate(code, reserved))
    }

    fun translate(dictionaryFile: File, toDictionary: File, reserved: Boolean = false, beforeAction: (File) -> Boolean) {
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

            toFile.also { file ->
                file.exists().ifFalse {
                    file.createNewFile()
                }
            }

            if (beforeAction(it)) {
                translate(it, toFile, reserved)
            } else {
                it.copyTo(toFile)
            }
        }
    }
}