package ru.raiffeisen.demoapplication.security.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.converters.PrincipalUserConverter
import ru.raiffeisen.demoapplication.exceptions.ResourceNotFoundException
import ru.raiffeisen.demoapplication.model.user.UserProfileModel
import ru.raiffeisen.demoapplication.repositories.UserRepository
import ru.raiffeisen.demoapplication.security.model.UserPrincipal
import ru.raiffeisen.demoapplication.utils.ExceptionsUtils
import java.lang.IllegalStateException

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository,
    private val principalUserConverter: PrincipalUserConverter
) : UserDetailsService {

    override fun loadUserByUsername(usernameOrEmail: String): UserDetails {
        val user =
            userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail) ?:
            throw UsernameNotFoundException("User not found with username or email : $usernameOrEmail")

        return convertToPrincipal(user)
    }

    fun loadUserById(id: Long): UserDetails {
        val user =
            userRepository.findByIdOrNull(id) ?:
            throw ResourceNotFoundException("User", "id", id)

        return convertToPrincipal(user)
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

    private fun convertToPrincipal(user: UserProfileModel): UserPrincipal {
        val conversionResult = principalUserConverter.convert(user)
        conversionResult.ifFailure { errorMessage ->
            throw IllegalStateException("An error occurred when converting to PrincipalUser: $errorMessage")
        }
        return conversionResult.value!!
    }
}
