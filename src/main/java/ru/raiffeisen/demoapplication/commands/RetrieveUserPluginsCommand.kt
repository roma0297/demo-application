package ru.raiffeisen.demoapplication.commands

import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.command.NoInputCommand
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.converters.MarketItemConverter
import ru.raiffeisen.demoapplication.dtos.response.UserPluginsDto
import ru.raiffeisen.demoapplication.extensions.mapOperationTransform
import ru.raiffeisen.demoapplication.security.service.CustomUserDetailsService
import ru.raiffeisen.demoapplication.services.UserProfileService

@Component
class RetrieveUserPluginsCommand(
    private val userPluginsService: UserProfileService,
    private val marketItemConverter: MarketItemConverter,
    //private val userContextService: UserContextService
    private val userContextService: CustomUserDetailsService
): NoInputCommand<OperationValueResult<UserPluginsDto>>() {

    override fun doProcess(): OperationValueResult<UserPluginsDto> {
        return userContextService.getCurrentUserId().flatMap { userId ->
            userPluginsService.getUserPlugins(userId).flatMap { marketItems ->
                marketItems
                    .mapOperationTransform { marketItemConverter.convert(it) }
                    .map { UserPluginsDto(it.toSet()) }
            }
        }
    }
}
