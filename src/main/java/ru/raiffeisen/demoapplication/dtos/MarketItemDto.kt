package ru.raiffeisen.demoapplication.dtos

data class MarketItemDto(
    val name: String,
    val url: String,
    val price: Double,
    val description: String
)