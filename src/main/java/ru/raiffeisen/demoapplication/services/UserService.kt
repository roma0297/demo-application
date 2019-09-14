package ru.raiffeisen.demoapplication.services

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.raiffeisen.demoapplication.model.UserProfileModel
import ru.raiffeisen.demoapplication.repositories.AuthorityRepository
import ru.raiffeisen.demoapplication.repositories.UserProfileRepository

@Service
class UserService(
    val userProfileRepository: UserProfileRepository,
    val authorityRepository: AuthorityRepository
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val foundUsers = userProfileRepository.findAllByEmail(email)

        if (foundUsers.isEmpty()) {
            throw UnsupportedOperationException("No users found for username $email")
        }

        if (foundUsers.size > 1) {
            throw UnsupportedOperationException("Multiple users found for username $email")
        }

        return foundUsers[0]
    }
}
