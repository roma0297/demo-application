package ru.raiffeisen.demoapplication.controllers

import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.raiffeisen.demoapplication.commands.RetrieveMarketItemsCommand
import ru.raiffeisen.demoapplication.dtos.ControllerError

@RestController
@RequestMapping("/account/marketplace")
class MarketingItemsController(private val marketItemsCommand: RetrieveMarketItemsCommand) {

    @GetMapping
    fun getMarketItems(@RequestParam("page", defaultValue = "0") page: Int,
                       @RequestParam("size", defaultValue = "10") size: Int): ResponseEntity<Any?> {
        val marketItemsResult = marketItemsCommand.process(PageRequest.of(page, size))

        marketItemsResult.ifFailure { errorMessage ->
            return ResponseEntity.badRequest().body(ControllerError(errorMessage))
        }
        return ResponseEntity.ok(marketItemsResult.value!!)
    }
}
