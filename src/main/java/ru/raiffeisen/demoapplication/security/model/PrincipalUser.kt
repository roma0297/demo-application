package ru.raiffeisen.demoapplication.security.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.raiffeisen.demoapplication.model.AbstractJpaPersistable
import ru.raiffeisen.demoapplication.model.authorization.UserCredentialsModel
import java.util.Objects

data class UserPrincipal(
    val name: String = "",
    private val username: String = "",
    @field:JsonIgnore val email: String = "",
    @field:JsonIgnore private val password: String = "",
    private val authorities: Collection<GrantedAuthority> = emptyList()
) : UserDetails, AbstractJpaPersistable<Long>() {

    override fun getUsername(): String = username

    override fun getPassword(): String = password

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as UserPrincipal?
        return getId() != null && getId() == that!!.getId()
    }

    override fun hashCode(): Int = Objects.hash(getId())

    companion object {
        fun create(user: UserCredentialsModel): UserPrincipal {
            val authorities = user.roles.map { role -> SimpleGrantedAuthority(role.name?.name) }

            return UserPrincipal(
                name = user.name ?: "",
                username = user.username ?: "",
                email = user.email ?: "",
                password = user.password ?: "",
                authorities = authorities
            )
        }
    }
}
