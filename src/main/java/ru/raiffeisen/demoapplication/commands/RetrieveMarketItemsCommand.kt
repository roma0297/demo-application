package ru.raiffeisen.demoapplication.commands

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.command.Command
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.converters.MarketItemConverter
import ru.raiffeisen.demoapplication.dtos.response.MarketItemDto
import ru.raiffeisen.demoapplication.extensions.mapOperationTransform
import ru.raiffeisen.demoapplication.services.MarketItemsService

@Component
class RetrieveMarketItemsCommand(
    private val marketItemsService: MarketItemsService,
    private val marketItemConverter: MarketItemConverter
) : Command<Pageable, OperationValueResult<Page<MarketItemDto>>> {

    override fun process(input: Pageable): OperationValueResult<Page<MarketItemDto>> {
        return marketItemsService.getMarketItems(input).flatMap { page ->
            page
                .mapOperationTransform { marketItemConverter.convert(it) }
                .map { PageImpl<MarketItemDto>(it) as Page<MarketItemDto> }
        }
    }
}
