package ru.raiffeisen.demoapplication.services

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.model.user.UserProfileModel
import ru.raiffeisen.demoapplication.repositories.UserProfileRepository

@Component
class UserContextService(
    private val userProfileRepository: UserProfileRepository
) {
    fun getCurrentUser(): OperationValueResult<UserProfileModel> {
        val userId = getCurrentUserId()
        return userId.flatMap { id ->
            val user = userProfileRepository.findByIdOrNull(id)
            if (user == null) {
                OperationValueResult.failure("No user with id $userId")
            } else {
                OperationValueResult.success(user)
            }
        }
    }

    fun getCurrentUserId(): OperationValueResult<Long> = OperationValueResult.success(0)
}
