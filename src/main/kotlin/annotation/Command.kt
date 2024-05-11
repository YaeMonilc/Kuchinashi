package ltd.bauhinia.annotation

annotation class Command(
    val aliases: Array<String> = [],
    val argsName: Array<String> = [],
    val argsType: Array<String> = [],
    val argsRequire: BooleanArray = [],
    val description: String = "",
    val enable: Boolean = true,
)
