package ru.raiffeisen.demoapplication.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
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
    val email: String,
    val userPassword: String,
    val firstName: String,
    val lastName: String,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_profile_market_items",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "market_item_id")]
    )
    val plugins: Set<MarketItemModel>,
    @OneToOne
    val picture: PictureMediaModel,
    @OneToMany(fetch = FetchType.EAGER)
    val userAuthorities: Set<AuthorityModel>
) : AbstractJpaPersistable<Int>(), UserDetails {

    override fun getAuthorities(): Set<GrantedAuthority> = userAuthorities

    override fun isEnabled(): Boolean = true

    override fun getUsername(): String = email

    override fun isCredentialsNonExpired(): Boolean = false

    override fun getPassword(): String = userPassword

    override fun isAccountNonExpired(): Boolean = false

    override fun isAccountNonLocked(): Boolean = false
}
