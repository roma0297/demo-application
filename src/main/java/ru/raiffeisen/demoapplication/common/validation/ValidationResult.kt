package ru.raiffeisen.demoapplication.common.validation

import ru.raiffeisen.demoapplication.common.operation.OperationResult
import ru.raiffeisen.demoapplication.common.operation.OperationResultCompanionObjectBuilder

class ValidationResult private constructor(error: String?) : OperationResult(error) {
    companion object : OperationResultCompanionObjectBuilder<ValidationResult>(::ValidationResult)
}
