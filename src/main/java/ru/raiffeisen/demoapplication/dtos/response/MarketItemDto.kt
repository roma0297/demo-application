package ru.raiffeisen.demoapplication.dtos.response

data class MarketItemDto(
    val id: Long,
    val name: String,
    val tabLabel: String,
    val description: String,
    val pageContent: String
)
