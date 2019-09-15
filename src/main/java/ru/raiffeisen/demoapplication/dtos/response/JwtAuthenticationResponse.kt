package ru.raiffeisen.demoapplication.dtos.response

data class JwtAuthenticationResponse(
    val accessToken: String,
    val tokenType: String = "Bearer"
)
