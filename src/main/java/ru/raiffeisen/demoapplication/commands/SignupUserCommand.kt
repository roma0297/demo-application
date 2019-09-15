package ru.raiffeisen.demoapplication.commands

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.command.Command
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.dtos.request.SignUpRequest
import ru.raiffeisen.demoapplication.dtos.response.ApiResponseDto
import ru.raiffeisen.demoapplication.model.authorization.RoleName
import ru.raiffeisen.demoapplication.model.authorization.UserCredentialsModel
import ru.raiffeisen.demoapplication.repositories.RoleRepository
import ru.raiffeisen.demoapplication.security.service.UserService

@Component
class SignupUserCommand(
    private val userService: UserService,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder
) : Command<SignUpRequest, OperationValueResult<ApiResponseDto>> {
    override fun process(input: SignUpRequest): OperationValueResult<ApiResponseDto> {
        val isExistsByUsername = userService.existsByUsername(input.username)
        if (isExistsByUsername.isFailure || isExistsByUsername.value!!) {
            return OperationValueResult.failure("Username is already taken!")
        }

        val isExistsEmail = userService.existsByEmail(input.email)
        if (isExistsEmail.isFailure || isExistsEmail.value!!) {
            return OperationValueResult.failure("Email Address already in use!")
        }

        val user = UserCredentialsModel(
            input.name,
            input.username,
            input.email,
            input.password
        )

        val userRole =
            roleRepository.findByName(RoleName.ROLE_USER) ?:
            return OperationValueResult.failure("There is no role ${RoleName.ROLE_USER} in the database")

        val encodedPassword = passwordEncoder.encode(user.password)
        val newUser = UserCredentialsModel(
            password = encodedPassword,
            roles = setOf(userRole)
        )
        val saveResult = userService.save(newUser)
        saveResult.ifFailure {
            return OperationValueResult.failure("Failed to save user")
        }

//        val location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/users/{username}")
//                .buildAndExpand(newUser.username).toUri()
        return OperationValueResult.success(ApiResponseDto(true, "Successfully registered new user"))
    }
}
