package hu.jozsefbarat.dogsshowcase.ext

import java.util.Locale

fun String.capitalize(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
}