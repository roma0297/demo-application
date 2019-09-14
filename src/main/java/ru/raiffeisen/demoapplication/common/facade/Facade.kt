package ru.raiffeisen.demoapplication.common.facade


import ru.raiffeisen.demoapplication.common.operation.OperationResult

/**
 * Interface used for processing between Controller and Service levels.
 */
interface Facade<in T, out OR : OperationResult> {
    fun process(input: T): OR
}
