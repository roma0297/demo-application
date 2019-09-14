package ru.raiffeisen.demoapplication.utils

object ExceptionsUtils {
    fun <T> tryOrNull(block: () -> T): T? {
        return try {
            block()
        } catch (_: Exception) {
            null
        }
    }
}
