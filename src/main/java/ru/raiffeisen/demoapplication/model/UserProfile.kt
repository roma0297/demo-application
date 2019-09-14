package ru.raiffeisen.demoapplication.model

import org.springframework.data.annotation.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

data class MarketItem(val none: String)

data class UserProfile(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Int,
        val firstName: String,
        val lastName: String,
        val plugins: List<MarketItem>
)