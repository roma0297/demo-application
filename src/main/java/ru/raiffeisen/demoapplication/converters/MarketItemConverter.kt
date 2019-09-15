package ru.raiffeisen.demoapplication.converters

import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.dtos.response.MarketItemDto
import ru.raiffeisen.demoapplication.model.user.MarketItemModel
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
                tabLabel = input.tabLabel,
                description = localizationService.localize(input.description),
                pageContent = input.pageContent
        ))
    }
}
