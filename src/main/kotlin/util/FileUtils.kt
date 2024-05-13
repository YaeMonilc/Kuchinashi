package ltd.bauhinia.util

import java.io.File

fun forEachFiles(dictionaryFile: File, action: (File) -> Unit) {
    dictionaryFile.listFiles()?.forEach {
        it.isFile.ifTrue {
            action(it)
        }
        it.isDirectory.ifTrue {
            forEachFiles(it, action)
        }
    }
}