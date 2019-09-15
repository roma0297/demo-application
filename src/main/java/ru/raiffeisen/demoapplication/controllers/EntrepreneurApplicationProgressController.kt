package ru.raiffeisen.demoapplication.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.raiffeisen.demoapplication.commands.RetrieveEntrepreneurApplicationStatusCommand
import ru.raiffeisen.demoapplication.dtos.response.ControllerError

@RestController("/api/user/application-progress")
class EntrepreneurApplicationProgressController(
    val entrepreneurApplicationStatusCommand: RetrieveEntrepreneurApplicationStatusCommand
) {

    @GetMapping
    fun getEntrepreneurApplicationProgress(): ResponseEntity<Any> {
        val entrepreneurApplicationStatuses = entrepreneurApplicationStatusCommand.doProcess()

        entrepreneurApplicationStatuses.ifFailure { errorMessage ->
            return ResponseEntity.badRequest().body(ControllerError(errorMessage))
        }
        return ResponseEntity.ok(entrepreneurApplicationStatuses.value!!)
    }

}