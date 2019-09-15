package ru.raiffeisen.demoapplication.dtos.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class SignUpRequest(
    @NotBlank
    val name: String? = null,

    @NotBlank
    val username: String? = null,

    @NotBlank
    @Email
    val email: String? = null,

    @NotBlank
    val password: String? = null
)
