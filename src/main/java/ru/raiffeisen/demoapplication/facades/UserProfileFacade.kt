package ru.raiffeisen.demoapplication.facades

import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.facade.ValidatedValueFacade
import ru.raiffeisen.demoapplication.model.UserProfile
import ru.raiffeisen.demoapplication.services.UserProfileService
import ru.raiffeisen.demoapplication.validator.UserIdValidator

@Component
class UserProfileFacade(
        private val userProfileService: UserProfileService,
        private val userIdValidator: UserIdValidator
): ValidatedValueFacade<String?, UserProfile>(userIdValidator) {
    override fun doProcess(validatedInput: String?): OperationValueResult<UserProfile> {
        return userProfileService.getUserPlugins(validatedInput!!.toInt())
    }
}
