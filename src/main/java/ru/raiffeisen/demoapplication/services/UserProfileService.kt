package ru.raiffeisen.demoapplication.services

import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.raiffeisen.demoapplication.common.operation.OperationResult
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.model.user.MarketItemModel
import ru.raiffeisen.demoapplication.model.user.UserProfileModel
import ru.raiffeisen.demoapplication.repositories.MarketItemsRepository
import ru.raiffeisen.demoapplication.repositories.UserProfileRepository

@Service
class UserProfileService(
    private val userProfileRepository: UserProfileRepository,
    private val marketItemsRepository: MarketItemsRepository
) {

    fun getUserProfile(userId: Long): OperationValueResult<UserProfileModel> {
        val userProfile = userProfileRepository.findByIdOrNull(userId) ?: run {
            val errorMessage = "Requested user $userId was not found in the database"
            logger.error(errorMessage)
            return OperationValueResult.failure(errorMessage)
        }
        return OperationValueResult.success(userProfile)
    }

    fun getUserPlugins(userId: Long): OperationValueResult<MutableSet<MarketItemModel>> {
        return getUserProfile(userId).map { it.plugins }
    }

    fun addPlugin(userId: Long, pluginId: Long): OperationResult {
        val pluginResult = findPluginById(pluginId)
        pluginResult.ifFailure { return OperationResult.failure(it) }

        val plugin = pluginResult.value!!
        return getUserProfile(userId).map { userProfile ->
            userProfile.plugins.plusAssign(plugin)
            updateUserProfile(
                userProfile,
                "Unable to add plugin $pluginId to the user profile"
            )
        }
    }

    fun removePlugin(userId: Long, pluginId: Long): OperationResult {
        val pluginResult = findPluginById(pluginId)
        pluginResult.ifFailure { return OperationResult.failure(it) }

        val plugin = pluginResult.value!!
        return getUserProfile(userId).map { userProfile ->
            userProfile.plugins.minusAssign(plugin)
            updateUserProfile(
                userProfile,
                "Unable to remove plugin $pluginId from the user profile"
            )
        }
    }

    private fun findPluginById(pluginId: Long): OperationValueResult<MarketItemModel> {
        return marketItemsRepository
            .findByIdOrNull(pluginId)
            ?.let { OperationValueResult.success(it) } ?: run {
            val errorMessage = "Requested plugin id $pluginId was not found in the database"
            logger.error(errorMessage)
            return OperationValueResult.failure(errorMessage)
        }
    }

    private fun updateUserProfile(userProfile: UserProfileModel,
                                  messageOnError: String): OperationResult {
        return try {
            userProfileRepository.save(userProfile)
            OperationResult.success()
        } catch (e: Exception) {
            val errorMessage = "$messageOnError: ${e.message}"
            logger.warn(errorMessage)
            OperationResult.failure(errorMessage)
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(UserProfileService::class.java)
    }
}
