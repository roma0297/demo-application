package ru.raiffeisen.demoapplication.converters

import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.dtos.MarketItemDto
import ru.raiffeisen.demoapplication.model.MarketItemModel
import ru.raiffeisen.demoapplication.services.LocalisationService

@Component
class MarketItemConverter(
    val localizationService: LocalisationService
) : Converter<MarketItemModel, MarketItemDto> {

    override fun convert(input: MarketItemModel): OperationValueResult<MarketItemDto> {
        return OperationValueResult.success(MarketItemDto(
                name = localizationService.localize(input.name),
                url = input.url,
                description = localizationService.localize(input.description)
        ))
    }
}
