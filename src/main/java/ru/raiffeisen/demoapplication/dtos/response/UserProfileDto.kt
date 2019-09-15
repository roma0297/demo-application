package ru.raiffeisen.demoapplication.dtos.response

import ru.raiffeisen.demoapplication.dtos.response.MarketItemDto

data class UserProfileDto(
        val firstName: String,
        val lastName: String,
        val plugins: List<MarketItemDto>
)
