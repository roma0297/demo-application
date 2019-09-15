package ru.raiffeisen.demoapplication.commands

import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.command.NoInputCommand
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.converters.EntrepreneurApplicationStatusConverter
import ru.raiffeisen.demoapplication.dtos.EntrepreneurApplicationStatusDto
import ru.raiffeisen.demoapplication.extensions.mapOperationTransform
import ru.raiffeisen.demoapplication.services.EntrepreneurApplicationStatusesService

@Component
class RetrieveEntrepreneurApplicationStatusCommand(
    private val entrepreneurApplicationStatusConverter: EntrepreneurApplicationStatusConverter,
    private val entrepreneurApplicationStatusesService: EntrepreneurApplicationStatusesService
) : NoInputCommand<OperationValueResult<List<EntrepreneurApplicationStatusDto>>>() {
    override fun doProcess(): OperationValueResult<List<EntrepreneurApplicationStatusDto>> {
        return entrepreneurApplicationStatusesService.getEntrepreneurApplicationStatuses().flatMap { statuses ->
            statuses.mapOperationTransform { entrepreneurApplicationStatusConverter.convert(it) }
        }
    }
}
