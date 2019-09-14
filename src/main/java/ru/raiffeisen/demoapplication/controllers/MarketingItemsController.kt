package ru.raiffeisen.demoapplication.controllers

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.raiffeisen.demoapplication.dtos.MarketItemDto
import ru.raiffeisen.demoapplication.facades.MarketItemsFacade

@RestController
@RequestMapping("/account/marketplace")
class MarketingItemsController(private val marketItemsFacade: MarketItemsFacade) {

    @GetMapping
    fun getMarketItems(@RequestParam("page") page: Int,
                       @RequestParam("size") size: Int): Page<MarketItemDto> {
        return marketItemsFacade.getMarketItems(PageRequest.of(page, size)).getOrDefault(Page.empty())
    }

}
