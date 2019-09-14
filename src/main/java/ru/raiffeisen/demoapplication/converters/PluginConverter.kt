package ru.raiffeisen.demoapplication.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.model.MarketItem

@Component
class PluginConverter : Converter<MarketItem, MarketItem> {
    override fun convert(source: MarketItem): MarketItem? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}