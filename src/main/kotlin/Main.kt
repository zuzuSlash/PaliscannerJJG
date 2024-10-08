package org.example

import java.io.File
import java.text.Normalizer

fun main() {
    val pathDir = "/home/juljimgar/PSP/PaliscannerJJG/src/main/Ficheros/"
    val dir = File(pathDir)

    if (!dir.isDirectory) {
        println("$pathDir no es un directorio válido")
        return
    } else {
        println("$pathDir es un directorio válido")
    }

    val filePath = dir.listFiles { item -> item.isFile }
        ?.toList()
        ?.map { it.path } ?: emptyList()

    filePath.forEach { filePath ->
        println("Contenido de archivo: $filePath")
    }

    filePath.forEach { filePath ->
        println("Contenido de archivo: $filePath")
        val content = File(filePath).readText()
        val normalizer = content.lowercase()
        val words = normalizer.split("\\s+".toRegex())

        var foundPalindromes = false
        for (word in words) {
            if (isPal(word)) {
                println("Palabra palíndroma encontrada: $word")
                foundPalindromes = true
            }
        }
    }
}


fun isPal(word: String): Boolean {
    val length = word.length
    for (i in 0 until length / 2) {
        if (word[i] != word[length - 1 - i]) {
            return false
        }
    }
    return true
}