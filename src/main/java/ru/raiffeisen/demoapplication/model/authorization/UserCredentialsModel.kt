package ru.raiffeisen.demoapplication.model.authorization

import org.hibernate.annotations.NaturalId
import ru.raiffeisen.demoapplication.model.AbstractJpaPersistable
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table
import javax.persistence.UniqueConstraint
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
@Table(
    name = "users",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["username"]),
        UniqueConstraint(columnNames = ["email"])
    ]
)
data class UserCredentialsModel(
    @NotBlank
    val name: String? = null,

    @NotBlank
    val username: String? = null,

    @NaturalId
    @NotBlank
    @Email
    val email: String? = null,

    @NotBlank
    val password: String? = null,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_roles",
        joinColumns = [
            JoinColumn(name = "user_id")
        ],
        inverseJoinColumns = [
            JoinColumn(name = "role_id")
        ]
    )
    val roles: Set<RoleModel> = hashSetOf()
) : AbstractJpaPersistable<Long>()
