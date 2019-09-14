package ru.raiffeisen.demoapplication.extensions

import ru.raiffeisen.demoapplication.common.operation.OperationResult
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult

//TODO(These functions can be implemented in parallel)
fun <T, R> Iterable<T>.mapOperationTransform(transform: (T) -> OperationValueResult<R>): OperationValueResult<List<R>> {
    val destination = arrayListOf<R>()
    for (element in this) {
        val transformedResult = transform(element)
        transformedResult.ifFailure { return OperationValueResult.failure(transformedResult.error!!) }
        destination += transformedResult.value!!
    }
    return OperationValueResult.success(destination)
}

fun Iterable<OperationResult>.mapOperationTransform(): OperationResult {
    return firstOrNull { it.isFailure } ?: OperationResult.success()
}
