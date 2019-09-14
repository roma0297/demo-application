package ru.raiffeisen.demoapplication.validator

import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.validation.Validator

@Component
class PluginIdValidator : Validator<String?>() {
    init {
        addValidation({ marketItemId -> marketItemId == null }, "No market item id is provided")
        addValidation({ marketItemId -> marketItemId?.toLongOrNull() == null }, "Expected market item id to be integer")
    }
}
