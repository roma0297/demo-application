package ru.raiffeisen.demoapplication.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.dtos.MarketItemDto
import ru.raiffeisen.demoapplication.model.MarketItemModel

@Component
class MarketItemConverter : Converter<MarketItemModel, MarketItemDto> {

    override fun convert(source: MarketItemModel): MarketItemDto? {
        val marketItemDto = MarketItemDto()
        marketItemDto.name = source.name?.russianTranslation ?: ""
        marketItemDto.description = source.description?.russianTranslation ?: ""
        return marketItemDto
    }
}
