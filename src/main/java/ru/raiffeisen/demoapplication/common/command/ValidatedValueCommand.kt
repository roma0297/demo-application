package ru.raiffeisen.demoapplication.common.command

import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.common.validation.Validator

abstract class ValidatedValueCommand<in T, R>(
        private val validator: Validator<T>
) : Command<T, OperationValueResult<R>> {

    override fun process(input: T): OperationValueResult<R> {
        val validationResult = validator.validate(input)

        if (validationResult.isFailure) {
            return OperationValueResult.failure(validationResult.getErrorOrNull()!!)
        }
        return doProcess(input)
    }

    abstract fun doProcess(validatedInput: T): OperationValueResult<R>
}
