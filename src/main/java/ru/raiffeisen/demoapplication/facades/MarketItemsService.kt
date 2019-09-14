package ru.raiffeisen.demoapplication.facades

import com.raiffeisen.javahack.core.operation.OperationValueResult
import org.springframework.core.convert.ConversionService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.dtos.MarketItemDto
import ru.raiffeisen.demoapplication.model.MarketItemModel
import ru.raiffeisen.demoapplication.services.MarketItemsService

@Component
class MarketItemsFacade(
    private val marketItemsService: MarketItemsService,
    private val conversionService: ConversionService
) {
    fun getMarketItems(pageable: Pageable): OperationValueResult<Page<MarketItemDto>> {

        return OperationValueResult.success(Page.empty<MarketItemDto>(pageable))
//        return marketItemsService.getMarketItems(pageable).map { page: Page<MarketItemModel> ->
//            page.map {
//                conversionService.convert(it, MarketItemDto::class.java)
//            }
//        }
    }
}