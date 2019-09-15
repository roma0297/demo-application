package ru.raiffeisen.demoapplication.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.ManyToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "market_items")
class MarketItemModel(
        @Column(name = "url")
        val url: String = "",
        @OneToOne
        val name: LocalizedStringModel? = null,
        @OneToOne
        val description: LocalizedStringModel? = null,
        @ManyToMany(mappedBy = "plugins")
        val users: Set<UserProfileModel> = emptySet(),
        @OneToOne
        val picture: PictureMediaModel? = null,
        @Lob
        @Column(name = "pageContent", columnDefinition="BLOB")
        val pageContent: String = ""
) : AbstractJpaPersistable<Long>()
