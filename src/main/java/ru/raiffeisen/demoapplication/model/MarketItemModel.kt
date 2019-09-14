package ru.raiffeisen.demoapplication.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "market_items")
class MarketItemModel(
    @Column(name = "label")
    val label: String = "",
    @OneToOne
    val name: LocalizedString? = null,
    @OneToOne
    val description: LocalizedString? = null
) : AbstractJpaPersistable<Long>()