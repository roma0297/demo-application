package ru.raiffeisen.demoapplication.converters

import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.dtos.EntrepreneurApplicationStatusDto
import ru.raiffeisen.demoapplication.model.EntrepreneurApplicationStatusModel
import ru.raiffeisen.demoapplication.services.LocalizationService
import ru.raiffeisen.demoapplication.services.UserContextService

@Component
class EntrepreneurApplicationStatusConverter(
    val localizationService: LocalizationService,
    val userContextService: UserContextService
) : Converter<EntrepreneurApplicationStatusModel, EntrepreneurApplicationStatusDto> {
    override fun convert(input: EntrepreneurApplicationStatusModel): OperationValueResult<EntrepreneurApplicationStatusDto> {
        val currentUser = userContextService.getCurrentUser()
        currentUser.ifFailure { return OperationValueResult.failure("Unable to retrieve user from the session") }

        return currentUser.map { userProfile ->
            EntrepreneurApplicationStatusDto(
                ordinalNumber = input.ordinalNumber,
                title = localizationService.localize(input.title),
                tooltipText = localizationService.localize(input.tooltipText),
                description = localizationService.localize(input.description),
                isCurrent = userProfile.currentStatus!!.ordinalNumber == input.ordinalNumber,
                isLastFinished = userProfile.currentStatus.ordinalNumber - 1 == input.ordinalNumber
            )
        }
    }
}
