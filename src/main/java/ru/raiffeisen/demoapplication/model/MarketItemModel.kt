package ru.raiffeisen.demoapplication.model

import javax.persistence.*

@Entity
@Table(name = "market_items")
class MarketItemModel(
        @Column(name = "label")
    val label: String = "",
        @OneToOne
    val name: LocalizedStringModel? = null,
        @OneToOne
    val description: LocalizedStringModel? = null,
        @ManyToMany(mappedBy = "plugins")
    val users: Set<UserProfileModel>
) : AbstractJpaPersistable<Long>()