package ru.raiffeisen.demoapplication.security.service

import org.springframework.stereotype.Service
import ru.raiffeisen.demoapplication.common.operation.OperationResult
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.model.authorization.UserCredentialsModel
import ru.raiffeisen.demoapplication.repositories.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun existsByUsername(username: String?): OperationValueResult<Boolean> {
        return OperationValueResult.success(username != null && userRepository.existsByUsername(username))
    }

    fun existsByEmail(email: String?): OperationValueResult<Boolean> {
        return OperationValueResult.success(email != null && userRepository.existsByEmail(email))
    }

    fun save(user: UserCredentialsModel): OperationResult {
        userRepository.save(user)
        return OperationResult.success()
    }
}
