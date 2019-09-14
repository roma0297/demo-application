package ru.raiffeisen.demoapplication.converters

import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.dtos.MarketItemDto
import ru.raiffeisen.demoapplication.model.MarketItemModel

@Component
class MarketItemConverter : Converter<MarketItemModel, MarketItemDto> {

    override fun convert(input: MarketItemModel): OperationValueResult<MarketItemDto> {
        return OperationValueResult.success(MarketItemDto(
                name = input.name?.russianTranslation ?: "",
                url = input.url,
                description = input.description?.russianTranslation ?: ""
        ))
    }
}
