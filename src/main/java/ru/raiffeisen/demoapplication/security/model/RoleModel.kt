package ru.raiffeisen.demoapplication.security.model

import org.hibernate.annotations.NaturalId
import ru.raiffeisen.demoapplication.model.AbstractJpaPersistable
import ru.raiffeisen.demoapplication.model.user.UserProfileModel
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.ManyToMany
import javax.persistence.Table

enum class RoleName {
    ROLE_USER,
    ROLE_ADMIN
}

@Entity
@Table(name = "roles")
data class RoleModel(
    @Enumerated(EnumType.STRING)
    val name: RoleName? = null,

    @ManyToMany(mappedBy = "roles")
    val users: Set<UserProfileModel> = emptySet()
) : AbstractJpaPersistable<Long>()
