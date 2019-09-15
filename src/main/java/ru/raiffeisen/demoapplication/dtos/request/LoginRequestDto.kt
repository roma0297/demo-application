package ru.raiffeisen.demoapplication.dtos.request

import javax.validation.constraints.NotBlank

data class LoginRequestDto(
    @NotBlank
    val usernameOrEmail: String? = null,

    @NotBlank
    val password: String? = null
)
