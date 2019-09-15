package ru.raiffeisen.demoapplication.dtos.response

data class UserProfileDto(
        val firstName: String,
        val lastName: String,
        val plugins: List<MarketItemDto>
)
