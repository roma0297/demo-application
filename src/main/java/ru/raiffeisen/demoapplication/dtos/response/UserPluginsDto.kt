package ru.raiffeisen.demoapplication.dtos.response

data class UserPluginsDto(
    val plugins: MutableSet<MarketItemDto>
)
