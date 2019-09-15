package ru.raiffeisen.demoapplication.commands

import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.command.ValidatedCommand
import ru.raiffeisen.demoapplication.common.operation.OperationResult
import ru.raiffeisen.demoapplication.security.service.CustomUserDetailsService
import ru.raiffeisen.demoapplication.services.UserProfileService
import ru.raiffeisen.demoapplication.validator.PluginIdValidator

@Component
class AddPluginToUserProfileCommand(
    private val userProfileService: UserProfileService,
    private val userContextService: CustomUserDetailsService,
    pluginIdValidator: PluginIdValidator
) : ValidatedCommand<String?>(pluginIdValidator) {

    override fun doProcess(validatedInput: String?): OperationResult {
        return userContextService.getCurrentUserId().map { userId ->
            val pluginId = validatedInput!!.toLong()
            return userProfileService.addPlugin(userId, pluginId)
        }
    }
}
