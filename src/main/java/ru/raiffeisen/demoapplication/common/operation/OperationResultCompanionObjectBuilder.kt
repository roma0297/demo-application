package ru.raiffeisen.demoapplication.common.operation

open class OperationResultCompanionObjectBuilder<T>(private val factory: (error: String?) -> T) {
    private val successSingleton = factory(null)

    fun success(): T = successSingleton

    fun failure(error: String) = factory(error)
}
