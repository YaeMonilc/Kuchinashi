package ltd.bauhinia.util

import org.reflections.Reflections

private val reflections = Reflections("ltd.bauhinia")

private fun processPackageName(packageName: String): String {
    return packageName.replace('.', '/')
}

fun <T> forEachSubTypes(clazz: Class<out T>, action: (clazz: Class<out T>) -> Unit) {
    reflections.getSubTypesOf(clazz).forEach {
        action(it)
    }
}
