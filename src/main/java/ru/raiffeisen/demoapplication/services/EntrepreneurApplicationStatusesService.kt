package ru.raiffeisen.demoapplication.services

import org.springframework.stereotype.Service
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.model.EntrepreneurApplicationStatusModel
import ru.raiffeisen.demoapplication.repositories.EntrepreneurApplicationStatusesRepository

@Service
class EntrepreneurApplicationStatusesService(
    val entrepreneurApplicationStatusesRepository: EntrepreneurApplicationStatusesRepository
) {
    fun getEntrepreneurApplicationStatuses(): OperationValueResult<List<EntrepreneurApplicationStatusModel>> {
        return OperationValueResult.success(entrepreneurApplicationStatusesRepository.findAll().sortedBy { it.ordinalNumber })
    }

}