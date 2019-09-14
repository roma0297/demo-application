package ru.raiffeisen.demoapplication.dtos

import ru.raiffeisen.demoapplication.model.MarketItem

data class UserProfileDto(
        val firstName: String,
        var lastName: String,
        val plugins: List<MarketItem>
)
