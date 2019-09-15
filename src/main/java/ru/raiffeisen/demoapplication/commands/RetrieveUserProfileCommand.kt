package ru.raiffeisen.demoapplication.commands

import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.command.NoInputCommand
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.converters.UserProfileConverter
import ru.raiffeisen.demoapplication.dtos.response.UserProfileDto
import ru.raiffeisen.demoapplication.security.service.CustomUserDetailsService
import ru.raiffeisen.demoapplication.services.UserProfileService

@Component
class RetrieveUserProfileCommand(
    private val userProfileService: UserProfileService,
    private val userProfileConverter: UserProfileConverter,
    //private val userContextService: UserContextService
    private val userContextService: CustomUserDetailsService
): NoInputCommand<OperationValueResult<UserProfileDto>>() {

    override fun doProcess(): OperationValueResult<UserProfileDto> {
        return userContextService.getCurrentUserId().flatMap { userId ->
            userProfileService.getUserProfile(userId).flatMap { userProfile ->
                userProfileConverter.convert(userProfile)
            }
        }
    }
}
