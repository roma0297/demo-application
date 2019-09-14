package ru.raiffeisen.demoapplication.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.raiffeisen.demoapplication.dtos.ControllerError
import ru.raiffeisen.demoapplication.commands.UserProfileCommand

@RestController
class UserProfileController(private val userProfileFacade: UserProfileCommand) {
    @GetMapping("/profile/{id}")
    fun getUserProfile(@PathVariable("id") userId: String?): ResponseEntity<Any> {
        val userProfileResult = userProfileFacade.process(userId)

        userProfileResult.ifFailure { errorMessage ->
            return ResponseEntity.badRequest().body(ControllerError(errorMessage))
        }
        return ResponseEntity.ok().build()
    }
}