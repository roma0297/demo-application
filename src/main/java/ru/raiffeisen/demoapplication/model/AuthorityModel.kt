package ru.raiffeisen.demoapplication.model

import org.springframework.security.core.GrantedAuthority
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "authorities")
class AuthorityModel : AbstractJpaPersistable<Long>(), GrantedAuthority {

    private var role: String? = null

    fun setRole(role: String) {
        this.role = role
    }

    override fun getAuthority(): String? {
        return role
    }
}