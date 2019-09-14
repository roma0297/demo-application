package ru.raiffeisen.demoapplication.converters

import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.dtos.UserProfileDto
import ru.raiffeisen.demoapplication.extensions.operationResultMap
import ru.raiffeisen.demoapplication.model.UserProfileModel

@Component
class UserProfileConverter(private val marketItemConverter: MarketItemConverter): Converter<UserProfileModel, UserProfileDto> {
    override fun convert(input: UserProfileModel): OperationValueResult<UserProfileDto> {
        val pluginsDtoResult = input.plugins
                .map { marketItemConverter.convert(it) }
                .operationResultMap()
                .map { it.toList() }

        return pluginsDtoResult.map { plugins ->
            UserProfileDto(
                firstName = input.firstName,
                lastName = input.lastName,
                plugins = plugins
            )
        }
    }
}
