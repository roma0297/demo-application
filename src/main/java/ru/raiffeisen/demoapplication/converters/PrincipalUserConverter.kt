package ru.raiffeisen.demoapplication.converters

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.model.user.UserProfileModel
import ru.raiffeisen.demoapplication.security.model.UserPrincipal

@Component
class PrincipalUserConverter : Converter<UserProfileModel, UserPrincipal> {
    override fun convert(input: UserProfileModel): OperationValueResult<UserPrincipal> {
        val authorities = input.roles.map { role -> SimpleGrantedAuthority(role.name?.name) }

        return OperationValueResult.success(UserPrincipal(
            firstName = input.firstName,
            lastName = input.lastName,
            username = input.username ?: "",
            email = input.email ?: "",
            password = input.password ?: "",
            authorities = authorities
        ))
    }
}
