package ru.raiffeisen.demoapplication.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.raiffeisen.demoapplication.security.model.RoleModel
import ru.raiffeisen.demoapplication.security.model.RoleName

@Repository
interface RoleRepository : CrudRepository<RoleModel, Long> {
    fun findByName(roleName: RoleName): RoleModel?
}
