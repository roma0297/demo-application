package ru.raiffeisen.demoapplication.dtos

data class EntrepreneurApplicationStatusDto(
    val ordinalNumber: Int,
    val title: String,
    val tooltipText: String,
    val description: String,
    val isLastFinished: Boolean = false,
    val isCurrent: Boolean = false
)