package ru.raiffeisen.demoapplication.commands

import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.command.ValidatedValueCommand
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.model.MarketItemModel
import ru.raiffeisen.demoapplication.services.UserProfileService
import ru.raiffeisen.demoapplication.validator.UserIdValidator

@Component
class RetrieveUserPluginsCommand(
        private val userProfileService: UserProfileService,
        userIdValidator: UserIdValidator
): ValidatedValueCommand<String?, Set<MarketItemModel>>(userIdValidator) {
    override fun doProcess(validatedInput: String?): OperationValueResult<Set<MarketItemModel>> {
        return userProfileService.getUserPlugins(validatedInput!!.toInt())
    }
}
