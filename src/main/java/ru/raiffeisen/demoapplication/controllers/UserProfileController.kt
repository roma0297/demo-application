package ru.raiffeisen.demoapplication.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.raiffeisen.demoapplication.commands.RetrieveUserPluginsCommand
import ru.raiffeisen.demoapplication.dtos.ControllerError
import ru.raiffeisen.demoapplication.commands.RetrieveUserProfileCommand

@RestController
class UserProfileController(private val userProfileCommand: RetrieveUserProfileCommand,
                            private val userPluginsCommand: RetrieveUserPluginsCommand) {
    @GetMapping("/account/{id}")
    fun getUserProfile(@PathVariable("id") userId: String?): ResponseEntity<Any> {
        val userProfileResult = userProfileCommand.process(userId)

        userProfileResult.ifFailure { errorMessage ->
            return ResponseEntity.badRequest().body(ControllerError(errorMessage))
        }
        return ResponseEntity.ok(userProfileResult.value!!)
    }

    @GetMapping("/account/plugins/{id}")
    fun getUserPlugins(@PathVariable("id") userId: String?): ResponseEntity<Any> {
        val userPluginsResult = userPluginsCommand.process(userId)

        userPluginsResult.ifFailure { errorMessage ->
            return ResponseEntity.badRequest().body(ControllerError(errorMessage))
        }
        return ResponseEntity.ok(userPluginsResult.value!!)
    }
}
