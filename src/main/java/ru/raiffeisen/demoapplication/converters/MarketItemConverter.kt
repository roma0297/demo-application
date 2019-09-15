package ru.raiffeisen.demoapplication.converters

import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.dtos.MarketItemDto
import ru.raiffeisen.demoapplication.model.MarketItemModel
import ru.raiffeisen.demoapplication.services.LocalizationService

@Component
class MarketItemConverter(
    val localizationService: LocalizationService
) : Converter<MarketItemModel, MarketItemDto> {

    override fun convert(input: MarketItemModel): OperationValueResult<MarketItemDto> {
        val id = input.getId() ?: return OperationValueResult.failure("MarketItemModel doesn't have primary key")
        return OperationValueResult.success(MarketItemDto(
                id = id,
                name = localizationService.localize(input.name),
                url = input.url,
                description = localizationService.localize(input.description),
                pageContent = input.pageContent
        ))
    }
}
