package ru.raiffeisen.demoapplication.services

import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.raiffeisen.demoapplication.model.UserProfileModel
import ru.raiffeisen.demoapplication.repositories.UserProfileRepository

@Service
class UserProfileService(private val userProfileRepository: UserProfileRepository) {

    fun getUserPlugins(userId: Int): OperationValueResult<UserProfileModel> {
        val userProfile = userProfileRepository.findByIdOrNull(userId) ?: run {
            val errorMessage = "Requested user $userId was not found in the database"
            logger.warn(errorMessage)
            return OperationValueResult.failure(errorMessage)
        }
        return OperationValueResult.success(userProfile)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(UserProfileService::class.java)
    }
}
