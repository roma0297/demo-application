package ru.raiffeisen.demoapplication.validator

import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.validation.Validator

@Component
class UserIdValidator : Validator<String?>() {
    init {
        addValidation({ userId -> userId == null }, "No user id is provided")
        addValidation({ userId -> userId?.toIntOrNull() == null }, "Expected user id to be integer")
    }
}
