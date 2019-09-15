package ru.raiffeisen.demoapplication.controllers

import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.raiffeisen.demoapplication.commands.RetrieveMarketItemCommand
import ru.raiffeisen.demoapplication.commands.RetrieveMarketItemsCommand
import ru.raiffeisen.demoapplication.dtos.response.ControllerError

@RestController
@RequestMapping("/api/account/marketplace")
class MarketingItemsController(val retrieveMarketItemsCommand: RetrieveMarketItemsCommand,
                               val retrieveMarketItemCommand: RetrieveMarketItemCommand) {

    @GetMapping
    fun getMarketItems(@RequestParam("page", defaultValue = "0") page: Int,
                       @RequestParam("size", defaultValue = "10") size: Int): ResponseEntity<Any?> {
        val marketItemsResult = retrieveMarketItemsCommand.process(PageRequest.of(page, size))

        marketItemsResult.ifFailure { errorMessage ->
            return ResponseEntity.badRequest().body(ControllerError(errorMessage))
        }
        return ResponseEntity.ok(marketItemsResult.value!!)
    }

    @GetMapping("/{marketingItemId}")
    fun getMarketingItem(@PathVariable marketingItemId: Long) : ResponseEntity<Any?> {
        val retrieveMarketItemCommandResult = retrieveMarketItemCommand.process(marketingItemId)
        retrieveMarketItemCommandResult.ifFailure { errorMessage ->
            return ResponseEntity.badRequest().body(ControllerError(errorMessage))
        }
        return ResponseEntity.ok(retrieveMarketItemCommandResult.value!!)
    }
}
