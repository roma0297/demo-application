package ru.raiffeisen.demoapplication.commands

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.command.Command
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.dtos.request.SignUpRequest
import ru.raiffeisen.demoapplication.dtos.response.ApiResponseDto
import ru.raiffeisen.demoapplication.model.user.UserProfileModel
import ru.raiffeisen.demoapplication.repositories.RoleRepository
import ru.raiffeisen.demoapplication.security.model.RoleName
import ru.raiffeisen.demoapplication.security.service.UserService
import ru.raiffeisen.demoapplication.services.EntrepreneurApplicationStatusesService

@Component
class SignupUserCommand(
    private val userService: UserService,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder,
    private val entrepreneurApplicationStatusesService: EntrepreneurApplicationStatusesService
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

        val statusesResult = entrepreneurApplicationStatusesService.getEntrepreneurApplicationStatuses()
        statusesResult.ifFailure { return OperationValueResult.failure("No status present for user") }
        val firstStatus = statusesResult.value!!.firstOrNull()

        val user = UserProfileModel(
            firstName = input.name ?: "",
            username = input.username,
            email = input.email,
            password = input.password,
            currentStatus = firstStatus
        )

        val userRole =
            roleRepository.findByName(RoleName.ROLE_USER)
                ?: return OperationValueResult.failure("There is no role ${RoleName.ROLE_USER} in the database")

        val encodedPassword = passwordEncoder.encode(user.password)
        val newUser = UserProfileModel(
            password = encodedPassword,
            roles = setOf(userRole),
            currentStatus = firstStatus
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
