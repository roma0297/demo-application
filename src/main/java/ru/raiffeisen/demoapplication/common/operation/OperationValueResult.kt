package ru.raiffeisen.demoapplication.common.operation

open class OperationValueResult<T> protected constructor(
    @PublishedApi internal val value: T?,
    error: String?
) : OperationResult(error) {

    fun getOrNull(): T? = value

    fun getOrDefault(defaultValue: T): T = value ?: defaultValue

    inline fun ifSuccess(action: (value: T) -> Unit) {
        if (isSuccess) action(value!!)
    }

    inline fun <R> map(transform: (value: T) -> R): OperationValueResult<R> {
        return when (value) {
            null -> failure(error!!)
            else -> success(transform(value))
        }
    }

    inline fun <R> flatMap(transform: (value: T) -> OperationValueResult<R>): OperationValueResult<R> {
        return when (value) {
            null -> failure(error!!)
            else -> transform(value)
        }
    }

    companion object {
        fun <T> failure(error: String): OperationValueResult<T> = OperationValueResult(null, error)

        fun <T> success(value: T): OperationValueResult<T> = OperationValueResult(value, null)
    }
}
