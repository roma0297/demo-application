package ru.raiffeisen.demoapplication.common

import org.mockito.Mockito

fun <T> any(): T {
    Mockito.any<T>()
    return uninitialized()
}

@Suppress("UNCHECKED_CAST")
private fun <T> uninitialized(): T = null as T
