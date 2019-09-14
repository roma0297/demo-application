package ru.raiffeisen.demoapplication.model

import javax.persistence.*

@Entity
@Table(name = "market_items")
class MarketItemModel(
        @Column(name = "url")
        val url: String = "",
        @Column(name = "price")
        val price: Double = 0.0,
        @OneToOne
        val name: LocalizedStringModel? = null,
        @OneToOne
        val description: LocalizedStringModel? = null,
        @ManyToMany(mappedBy = "plugins")
        val users: Set<UserProfileModel> = emptySet(),
        @OneToOne
        val picture: PictureMediaModel? = null
) : AbstractJpaPersistable<Long>()
