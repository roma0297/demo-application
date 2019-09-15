package ru.raiffeisen.demoapplication.converters

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.dtos.EntrepreneurApplicationStatusDto
import ru.raiffeisen.demoapplication.model.EntrepreneurApplicationStatusModel
import ru.raiffeisen.demoapplication.model.user.UserProfileModel
import ru.raiffeisen.demoapplication.services.LocalizationService

@Component
class EntrepreneurApplicationStatusConverter(
    val localizationService: LocalizationService
) : Converter<EntrepreneurApplicationStatusModel, EntrepreneurApplicationStatusDto> {
    override fun convert(input: EntrepreneurApplicationStatusModel): OperationValueResult<EntrepreneurApplicationStatusDto> {
        val currentUser = SecurityContextHolder.getContext().authentication.principal

        (currentUser as? UserProfileModel)?.let {
            return OperationValueResult.success(EntrepreneurApplicationStatusDto(
                ordinalNumber = input.ordinalNumber,
                title = localizationService.localize(input.title),
                tooltipText = localizationService.localize(input.tooltipText),
                description = localizationService.localize(input.description),
                isCurrent = currentUser.currentStatus!!.ordinalNumber == input.ordinalNumber,
                isLastFinished = currentUser.currentStatus!!.ordinalNumber - 1 == input.ordinalNumber
            ))
        }

        return OperationValueResult.failure("Current user is not authorized")
    }
}