package ru.raiffeisen.demoapplication.services

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.model.MarketItemModel
import ru.raiffeisen.demoapplication.repositories.MarketItemsRepository

@Component
class MarketItemsService(
    private val marketItemsRepository: MarketItemsRepository
) {
    fun getMarketItems(pageable: Pageable): OperationValueResult<Page<MarketItemModel>> {
        return OperationValueResult.success(marketItemsRepository.findAll(pageable))
    }
}