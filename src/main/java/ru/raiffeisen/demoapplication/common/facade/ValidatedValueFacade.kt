package ru.raiffeisen.demoapplication.common.facade

import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.common.validation.Validator

abstract class ValidatedValueFacade<in T, R>(
        private val validator: Validator<T>
) : Facade<T, OperationValueResult<R>> {

    override fun process(input: T): OperationValueResult<R> {
        val validationResult = validator.validate(input)

        if (validationResult.isFailure) {
            return OperationValueResult.failure(validationResult.getErrorOrNull()!!)
        }

        return doProcess(input)
    }

    abstract fun doProcess(validatedInput: T): OperationValueResult<R>
}
