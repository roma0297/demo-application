package ru.raiffeisen.demoapplication.repositories;

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import ru.raiffeisen.demoapplication.model.MarketItemModel

@Repository
interface MarketItemsRepository : PagingAndSortingRepository<MarketItemModel, Long>
