package ru.raiffeisen.demoapplication.services

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.model.UserProfileModel
import ru.raiffeisen.demoapplication.repositories.UserProfileRepository
import ru.raiffeisen.demoapplication.utils.ExceptionsUtils

@Component
class UserContextService(
    private val userProfileRepository: UserProfileRepository
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val foundUsers = userProfileRepository.findAllByEmail(email)

        if (foundUsers.isEmpty()) {
            throw UnsupportedOperationException("No users found for username $email")
        }
        if (foundUsers.size > 1) {
            throw UnsupportedOperationException("Multiple users found for username $email")
        }
        return foundUsers[0]
    }

    fun getCurrentUser(): OperationValueResult<UserProfileModel> {
        val userIdOrNull = ExceptionsUtils.tryOrNull {
            SecurityContextHolder.getContext().authentication.principal as UserProfileModel
        }
        userIdOrNull ?: return OperationValueResult.failure("There is no user in the current session")
        return OperationValueResult.success(userIdOrNull)
    }

    fun getCurrentUserId(): OperationValueResult<Long> {
        return getCurrentUser().map { userProfile ->
            userProfile.getId() ?: return OperationValueResult.failure("There is no user id in the current session")
        }
    }
}
