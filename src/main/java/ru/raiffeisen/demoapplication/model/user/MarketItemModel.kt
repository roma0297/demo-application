package ru.raiffeisen.demoapplication.model.user

import ru.raiffeisen.demoapplication.model.AbstractJpaPersistable
import ru.raiffeisen.demoapplication.model.localization.LocalizedStringModel
import ru.raiffeisen.demoapplication.model.media.PictureMediaModel
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.ManyToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "market_items")
class MarketItemModel(
    @Column(name = "tab_label")
    val tabLabel: String = "",
    @OneToOne
    val name: LocalizedStringModel? = null,
    @OneToOne
    val description: LocalizedStringModel? = null,
    @ManyToMany(mappedBy = "plugins")
    val users: Set<UserProfileModel> = emptySet(),
    @OneToOne
    val picture: PictureMediaModel? = null,
    @Column(name = "itemName")
    val itemName: String = "",
    @Column(name = "subtitle")
    val subtitle: String = "",
    @Column(name = "url")
    val url: String = "",
    @Column(name = "is_enabled")
    val isEnabled: Boolean = false,
    @Column(name = "is_shown")
    val isShown: Boolean = true,
    @Lob
    @Column(name = "pageContent", columnDefinition = "BLOB")
    val pageContent: String = ""
) : AbstractJpaPersistable<Long>()
