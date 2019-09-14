package ru.raiffeisen.demoapplication.extensions

import ru.raiffeisen.demoapplication.common.operation.OperationResult
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult

fun <T> Collection<OperationValueResult<T>>.operationResultMap(): OperationValueResult<Collection<T>> {
    TODO()
}

fun Collection<OperationResult>.operationResultMap(): OperationResult {
    val firstErrorResult = firstOrNull { it.isFailure }
    return if (firstErrorResult == null) {
        OperationResult.success()
    } else {
        OperationResult.failure(firstErrorResult.error!!)
    }
}
