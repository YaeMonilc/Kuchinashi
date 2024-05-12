package ltd.bauhinia.annotation

annotation class Command(
    val aliases: Array<String> = [],
    val args: Array<String> = [],
    val description: String = "",
    val enable: Boolean = true,
)
