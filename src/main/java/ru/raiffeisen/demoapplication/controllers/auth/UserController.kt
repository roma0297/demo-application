package ru.raiffeisen.demoapplication.controllers.auth

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.raiffeisen.demoapplication.dtos.response.UserSummary
import ru.raiffeisen.demoapplication.security.model.CurrentUser
import ru.raiffeisen.demoapplication.security.model.UserPrincipal

@RestController
@RequestMapping("/api")
class UserController {

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    fun getCurrentUser(@CurrentUser currentUser: UserPrincipal): ResponseEntity<Any> {
        val userSummary = UserSummary(currentUser.getId(), currentUser.username, currentUser.name)

        return ResponseEntity.ok(userSummary)
    }
}
