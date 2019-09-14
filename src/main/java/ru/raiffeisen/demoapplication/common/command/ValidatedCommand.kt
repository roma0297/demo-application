package ru.raiffeisen.demoapplication.common.command

import ru.raiffeisen.demoapplication.common.operation.OperationResult
import ru.raiffeisen.demoapplication.common.validation.Validator

abstract class ValidatedCommand<in T>(
        private val validator: Validator<T>
) : Command<T, OperationResult> {

    override fun process(input: T): OperationResult {
        val validationResult = validator.validate(input)

        if (validationResult.isFailure) {
            return validationResult
        }

        return doProcess(input)
    }

    abstract fun doProcess(validatedInput: T): OperationResult
}
