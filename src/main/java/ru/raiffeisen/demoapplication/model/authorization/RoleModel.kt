package ru.raiffeisen.demoapplication.model.authorization

import org.hibernate.annotations.NaturalId
import ru.raiffeisen.demoapplication.model.AbstractJpaPersistable
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

enum class RoleName {
    ROLE_USER,
    ROLE_ADMIN
}

@Entity
@Table(name = "roles")
data class RoleModel(
    @Enumerated(EnumType.STRING)
    @NaturalId
    val name: RoleName? = null
) : AbstractJpaPersistable<Long>()
