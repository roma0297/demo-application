package ru.raiffeisen.demoapplication.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.raiffeisen.demoapplication.model.user.UserProfileModel

@Repository
interface UserRepository : CrudRepository<UserProfileModel, Long> {
    fun findByUsernameOrEmail(username: String, email: String): UserProfileModel?

    fun existsByUsername(username: String): Boolean

    fun existsByEmail(email: String): Boolean
}
