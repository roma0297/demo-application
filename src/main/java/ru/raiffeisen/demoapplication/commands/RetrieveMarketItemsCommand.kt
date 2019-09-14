package ru.raiffeisen.demoapplication.commands

import org.springframework.core.convert.ConversionService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.command.Command
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.dtos.MarketItemDto
import ru.raiffeisen.demoapplication.services.MarketItemsService

@Component
class RetrieveMarketItemsCommand(
    private val marketItemsService: MarketItemsService,
    private val conversionService: ConversionService
): Command<Pageable, OperationValueResult<Page<MarketItemDto>>> {
    override fun process(input: Pageable): OperationValueResult<Page<MarketItemDto>> {
        return marketItemsService.getMarketItems(input).map { page ->
            page.map {
                conversionService.convert(it, MarketItemDto::class.java)
            }
        }
    }
}