package org.example

import java.io.File
import java.text.Normalizer

fun main() {
    val pathDir = "C:\\Users\\Zzz\\Desktop\\PaliscannerJJG\\src\\main\\Ficheros"
    val dir = File(pathDir)

    // Verificar directorio
    if (!dir.isDirectory) {
        println("$pathDir no es un directorio válido")
        return
    } else {
        println("$pathDir es un directorio válido")
    }

    // Obtener lista de ficheros
    val filePath = dir.listFiles { item -> item.isFile }
        ?.toList()
        ?.map { it.path } ?: emptyList()

    // Proceso de cada archivo
    filePath.forEach { file ->
        println("Procesando archivo: $file")
        val content = File(file).readText()

        // Separar las palabras, eliminando caracteres no alfabéticos y almacenando original y normalizada
        val words = ArrayList<Pair<String, String>>()
        content.split(Regex("[\\s,;:.!?¿¡]+")).forEach { word ->
            val normalized = word.lowercase().normalize()

            words.add(Pair(word, normalized))
        }

        // Buscar los palíndromos
        var foundPalindromes = false
        words.forEach { (original, normalized) ->
            if (isPal(normalized) && normalized.isNotEmpty()) {
                println("Palabra palíndroma encontrada: $original")
                foundPalindromes = true
            }
        }

        // Si no se encuentran palíndromos
        if (!foundPalindromes) {
            println("No se encontraron palíndromos en el archivo: $file")
        }
    }
}

// Verificar si es palíndromo
fun isPal(word: String): Boolean {
    val length = word.length
    for (i in 0 until length / 2) {
        if (word[i] != word[length - 1 - i]) {
            return false
        }
    }
    return true
}

// Función para eliminar acentos
fun String.normalize(): String {
    return Normalizer.normalize(this, Normalizer.Form.NFD)
        .replace(Regex("[\\p{InCombiningDiacriticalMarks}]"), "")
        .replace(Regex("[ya\"]"), "")
        .replace("la", "")
        .replace(Regex("[^a-záéíóúñ]"), "")
}