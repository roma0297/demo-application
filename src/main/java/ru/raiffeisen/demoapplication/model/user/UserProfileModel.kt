package ru.raiffeisen.demoapplication.model.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.raiffeisen.demoapplication.model.AbstractJpaPersistable
import ru.raiffeisen.demoapplication.model.media.PictureMediaModel
import java.util.Locale
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "user_profile")
data class UserProfileModel(
    val firstName: String = "",
    val lastName: String = "",
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_profile_market_items",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "market_item_id")]
    )
    val plugins: Set<MarketItemModel> = emptySet(),
    @OneToOne
    val picture: PictureMediaModel? = null,
    val locale: Locale = Locale.ENGLISH
) : AbstractJpaPersistable<Long>()
