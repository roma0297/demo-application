package ru.raiffeisen.demoapplication.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.raiffeisen.demoapplication.model.authorization.UserCredentialsModel

@Repository
interface UserRepository : CrudRepository<UserCredentialsModel, Long> {
    fun findByEmail(email: String): UserCredentialsModel?

    fun findByUsernameOrEmail(username: String, email: String): UserCredentialsModel?

    fun findByIdIn(userIds: List<Long>): UserCredentialsModel?

    fun findByUsername(username: String): UserCredentialsModel?

    fun existsByUsername(username: String): Boolean

    fun existsByEmail(email: String): Boolean
}
