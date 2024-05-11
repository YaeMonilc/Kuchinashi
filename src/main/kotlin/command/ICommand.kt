package ltd.bauhinia.command

interface ICommand {
    fun execute(args: Map<String, String>)
}