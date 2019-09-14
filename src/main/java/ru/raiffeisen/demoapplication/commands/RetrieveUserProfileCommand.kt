package ru.raiffeisen.demoapplication.commands

import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.command.ValidatedValueCommand
import ru.raiffeisen.demoapplication.model.UserProfileModel
import ru.raiffeisen.demoapplication.services.UserProfileService
import ru.raiffeisen.demoapplication.validator.UserIdValidator

@Component
class RetrieveUserProfileCommand(
        private val userProfileService: UserProfileService,
        userIdValidator: UserIdValidator
): ValidatedValueCommand<String?, UserProfileModel>(userIdValidator) {
    override fun doProcess(validatedInput: String?): OperationValueResult<UserProfileModel> {
        return userProfileService.getUserProfile(validatedInput!!.toInt())
    }
}
