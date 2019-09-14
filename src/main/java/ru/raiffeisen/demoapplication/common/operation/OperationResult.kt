package ru.raiffeisen.demoapplication.common.operation

open class OperationResult protected constructor(@PublishedApi internal val error: String?) {

    init {
        require(!(error == null || error.isBlank())) { "Error message shouldn't be blank" }
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

