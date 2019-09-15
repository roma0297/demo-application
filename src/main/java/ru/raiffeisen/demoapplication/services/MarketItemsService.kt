package ru.raiffeisen.demoapplication.services

import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
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

    fun getMarketItem(marketItemId: Long): OperationValueResult<MarketItemModel> {
        val marketItem = marketItemsRepository.findByIdOrNull(marketItemId)
        return marketItem?.let { OperationValueResult.success(it) } ?: run {
            val errorMessage = "Requested market item id $marketItemId was not found in the database"
            logger.error(errorMessage)
            return OperationValueResult.failure(errorMessage)
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(MarketItemsService::class.java)
    }
}
