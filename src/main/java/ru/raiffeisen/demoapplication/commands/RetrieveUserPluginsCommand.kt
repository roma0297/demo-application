package ru.raiffeisen.demoapplication.commands

import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.command.ValidatedValueCommand
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.converters.MarketItemConverter
import ru.raiffeisen.demoapplication.dtos.UserPluginsDto
import ru.raiffeisen.demoapplication.extensions.listMapTransform
import ru.raiffeisen.demoapplication.services.UserProfileService
import ru.raiffeisen.demoapplication.validator.UserIdValidator

@Component
class RetrieveUserPluginsCommand(
    private val userPluginsService: UserProfileService,
    private val marketItemConverter: MarketItemConverter,
    userIdValidator: UserIdValidator
): ValidatedValueCommand<String?, UserPluginsDto>(userIdValidator) {

    @Suppress("UNCHECKED_CAST")
    override fun doProcess(validatedInput: String?): OperationValueResult<UserPluginsDto> {
        return userPluginsService.getUserPlugins(validatedInput!!.toInt()).flatMap { marketItems ->
            marketItems
                .map { marketItemConverter.convert(it) }
                .listMapTransform()
                .map { UserPluginsDto(it.toSet()) }
        }
    }
}
