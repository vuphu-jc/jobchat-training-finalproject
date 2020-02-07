package com.example.minijobchat.utils

object StringUtils {
    fun isPhoneNumber(text: String): Boolean {
        val numberPattern = "^[+]*[(]?[0-9]{1,4}[)]?[-\\s./0-9]*\$".toRegex()
        return numberPattern.matches(text)
    }

    fun isEmail(text: String): Boolean {
        val emailPattern = "^[a-z][a-z0-9_.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}\$".toRegex()
        return emailPattern.matches(text)
    }

    fun filterNumber(text: String): String {
        return text.filter { it -> it in '0'..'9' }
    }
}
