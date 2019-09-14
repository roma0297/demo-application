package ru.raiffeisen.demoapplication.repositories

import org.springframework.data.repository.CrudRepository
import ru.raiffeisen.demoapplication.model.UserProfile

interface UserProfileRepository : CrudRepository<UserProfile, Int>