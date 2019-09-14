package ru.raiffeisen.demoapplication.model

import javax.persistence.*

@Entity
@Table(name = "user_profile")
data class UserProfileModel(
        val firstName: String,
        val lastName: String,
        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "user_profile_market_items",
                joinColumns = [JoinColumn(name = "user_id")],
                inverseJoinColumns = [JoinColumn(name = "market_item_id")]
        )
        val plugins: Set<MarketItemModel>
) : AbstractJpaPersistable<Int>()
