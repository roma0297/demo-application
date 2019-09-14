package ru.raiffeisen.demoapplication.services

import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.raiffeisen.demoapplication.model.MarketItemModel
import ru.raiffeisen.demoapplication.model.UserProfileModel
import ru.raiffeisen.demoapplication.repositories.UserProfileRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService


@Service
class UserProfileService(private val userProfileRepository: UserProfileRepository) : UserDetailsService{

    fun getUserProfile(userId: Int): OperationValueResult<UserProfileModel> {
        val userProfile = userProfileRepository.findByIdOrNull(userId) ?: run {
            val errorMessage = "Requested user $userId was not found in the database"
            logger.warn(errorMessage)
            return OperationValueResult.failure(errorMessage)
        }
        return OperationValueResult.success(userProfile)
    }

    fun getUserPlugins(userId: Int): OperationValueResult<Set<MarketItemModel>> {
        return getUserProfile(userId).map { it.plugins }
    }

    fun getCurrentUser(): UserProfileModel? {
        return try {
            SecurityContextHolder.getContext().authentication.principal as UserProfileModel
        } catch (e: Exception) {
            null
        }

    }

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

    companion object {
        private val logger = LoggerFactory.getLogger(UserProfileService::class.java)
    }
}
