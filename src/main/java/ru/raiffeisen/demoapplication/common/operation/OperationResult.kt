package com.raiffeisen.javahack.core.operation

open class OperationResult protected constructor(@PublishedApi internal val error: String?) {

    init {
        error?.ifBlank {
            throw IllegalArgumentException("Error message shouldn't be blank")
        }
    }

    companion object : OperationResultCompanionObjectBuilder<OperationResult>(::OperationResult)

    val isSuccess: Boolean
        get() = error == null

    val isFailure: Boolean
        get() = !isSuccess

    fun getErrorOrNull(): String? = error

    inline fun mapError(transform: (error: String) -> String): OperationResult {
        return when {
            isFailure -> failure(transform(error!!))
            else -> this
        }
    }

    inline fun ifSuccess(action: () -> Unit) {
        if (isSuccess) action()
    }

    inline fun ifFailure(action: (error: String) -> Unit) {
        if (isFailure) action(error!!)
    }
}

