package ru.raiffeisen.demoapplication.common.command


import ru.raiffeisen.demoapplication.common.operation.OperationResult

/**
 * Interface used for processing between Controller and Service levels.
 */
interface Command<in T, out OR : OperationResult> {
    fun process(input: T): OR
}
