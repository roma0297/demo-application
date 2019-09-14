package ru.raiffeisen.demoapplication.dtos

data class UserProfileDto(
        val firstName: String,
        var lastName: String,
        val plugins: List<MarketItemDto>
)
