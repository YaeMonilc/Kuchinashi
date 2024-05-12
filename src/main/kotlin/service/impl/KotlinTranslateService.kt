package ltd.bauhinia.service.impl

import ltd.bauhinia.enum.KotlinTokens
import ltd.bauhinia.service.IService
import ltd.bauhinia.util.logger

object KotlinTranslateService : IService {
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

    private fun translateToken(token:String): String? {
        return KotlinTokens.getByAliasName(token)?.originName
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
}