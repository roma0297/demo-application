package ru.raiffeisen.demoapplication.common.validation

abstract class Validator<T> {
    private val validations : MutableList<Pair<(T) -> Boolean, String>> = mutableListOf()

    fun validate(objectToValidate: T) : ValidationResult {
        for(validation in validations) {
            if (validation.first(objectToValidate)) {
                return ValidationResult.failure(validation.second)
            }
        }

        return ValidationResult.success()
    }

    protected fun addValidation(validation: (T) -> Boolean, errorMessage: String) {
        validations += Pair(validation, errorMessage)
    }
}
