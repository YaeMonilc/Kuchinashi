package ltd.bauhinia.util

fun Boolean.ifTrue(action: () -> Unit) {
    if (this)
        action()
}

fun Boolean.ifFalse(action: () -> Unit) {
    (!this).ifTrue {
        action()
    }
}