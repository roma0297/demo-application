package ru.raiffeisen.demoapplication.common.command

import ru.raiffeisen.demoapplication.common.operation.OperationResult

interface Command<in T, out OR : OperationResult> {
    fun process(input: T): OR
}
