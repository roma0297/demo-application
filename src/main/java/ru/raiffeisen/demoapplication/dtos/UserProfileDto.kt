package ru.raiffeisen.demoapplication.dtos

data class UserProfileDto(
        val firstName: String,
        val lastName: String,
        val plugins: List<MarketItemDto>
)
