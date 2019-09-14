package ru.raiffeisen.demoapplication.commands

import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.command.ValidatedValueCommand
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.converters.UserProfileConverter
import ru.raiffeisen.demoapplication.dtos.UserProfileDto
import ru.raiffeisen.demoapplication.services.UserProfileService
import ru.raiffeisen.demoapplication.validator.UserIdValidator

@Component
class RetrieveUserProfileCommand(
    private val userProfileService: UserProfileService,
    private val userProfileConverter: UserProfileConverter,
    userIdValidator: UserIdValidator
): ValidatedValueCommand<String?, UserProfileDto>(userIdValidator) {
    override fun doProcess(validatedInput: String?): OperationValueResult<UserProfileDto> {
        return userProfileService.getUserProfile(validatedInput!!.toInt()).flatMap { userProfile ->
            userProfileConverter.convert(userProfile)
        }
    }
}
