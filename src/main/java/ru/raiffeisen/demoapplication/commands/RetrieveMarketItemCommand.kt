package ru.raiffeisen.demoapplication.commands

import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.command.Command
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.converters.MarketItemConverter
import ru.raiffeisen.demoapplication.dtos.response.MarketItemDto
import ru.raiffeisen.demoapplication.services.MarketItemsService

@Component
class RetrieveMarketItemCommand(val marketItemsService: MarketItemsService,
                                val marketItemConverter: MarketItemConverter) : Command<Long, OperationValueResult<MarketItemDto>> {
    override fun process(input: Long): OperationValueResult<MarketItemDto> {
        return marketItemsService.getMarketItem(input).flatMap { marketItemConverter.convert(it) }
    }

}
