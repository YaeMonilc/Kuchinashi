package ltd.bauhinia.util

fun Boolean.ifTrue(condition: () -> Unit) {
    if (this)
        condition()
}

fun Boolean.ifFalse(condition: () -> Unit) {
    (!this).ifTrue {
        condition()
    }
}