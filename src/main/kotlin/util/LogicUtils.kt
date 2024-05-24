package ltd.bauhinia.util

infix fun Boolean.and(other: Boolean) = this && other

infix fun Boolean.or(other: Boolean) = this || other

fun Boolean.ifTrue(action: () -> Unit) {
    if (this)
        action()
}

fun Boolean.ifFalse(action: () -> Unit) {
    (!this).ifTrue {
        action()
    }
}