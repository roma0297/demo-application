package ru.raiffeisen.demoapplication.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.raiffeisen.demoapplication.commands.AddPluginToUserProfileCommand
import ru.raiffeisen.demoapplication.commands.RemovePluginToUserProfileCommand
import ru.raiffeisen.demoapplication.commands.RetrieveUserPluginsCommand
import ru.raiffeisen.demoapplication.dtos.response.ControllerError
import ru.raiffeisen.demoapplication.commands.RetrieveUserProfileCommand

@RestController
@RequestMapping("/api")
class UserProfileController(
    private val userProfileCommand: RetrieveUserProfileCommand,
    private val userPluginsCommand: RetrieveUserPluginsCommand,
    private val addPluginToUserProfileCommand: AddPluginToUserProfileCommand,
    private val removePluginToUserProfileCommand: RemovePluginToUserProfileCommand
) {

    @GetMapping("/account")
    fun getUserProfile(): ResponseEntity<Any> {
        val userProfileResult = userProfileCommand.process(Unit)

        userProfileResult.ifFailure { errorMessage ->
            return ResponseEntity.badRequest().body(ControllerError(errorMessage))
        }
        return ResponseEntity.ok(userProfileResult.value!!)
    }

    @GetMapping("/account/plugins")
    fun getUserPlugins(): ResponseEntity<Any> {
        val userPluginsResult = userPluginsCommand.process(Unit)

        userPluginsResult.ifFailure { errorMessage ->
            return ResponseEntity.badRequest().body(ControllerError(errorMessage))
        }
        return ResponseEntity.ok(userPluginsResult.value!!)
    }

    @PostMapping("/account/plugins/add/{pluginId}")
    fun addUserPlugin(@PathVariable pluginId: String?): ResponseEntity<Any> {
        val addPluginResult = addPluginToUserProfileCommand.process(pluginId)

        addPluginResult.ifFailure { errorMessage ->
            return ResponseEntity.badRequest().body(ControllerError(errorMessage))
        }
        return ResponseEntity.ok().build()
    }

    @PostMapping("/account/plugins/remove/{pluginId}")
    fun removeUserPlugin(@PathVariable pluginId: String?): ResponseEntity<Any> {
        val addPluginResult = removePluginToUserProfileCommand.process(pluginId)

        addPluginResult.ifFailure { errorMessage ->
            return ResponseEntity.badRequest().body(ControllerError(errorMessage))
        }
        return ResponseEntity.ok().build()
    }
}
