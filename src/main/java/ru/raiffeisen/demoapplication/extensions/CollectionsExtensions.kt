package ru.raiffeisen.demoapplication.extensions

import ru.raiffeisen.demoapplication.common.operation.OperationResult
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult

fun <T> Iterable<OperationValueResult<T>>.mapTransform(): OperationValueResult<Iterable<T>> {
    val firstErrorResult = firstOrNull { it.isFailure }
    return if (firstErrorResult == null) {
        OperationValueResult.success(map { it.value!! })
    } else {
        OperationValueResult.failure(firstErrorResult.error!!)
    }
}

fun <T> List<OperationValueResult<T>>.listMapTransform(): OperationValueResult<List<T>> {
    return mapTransform().map { it as List<T> }
}

fun <T> MutableList<OperationValueResult<T>>.mutableListMapTransform(): OperationValueResult<MutableList<T>> {
    return mapTransform().map { it as MutableList<T> }
}

fun Iterable<OperationResult>.mapTransform(): OperationResult {
    return firstOrNull { it.isFailure } ?: OperationResult.success()
}
