package ru.raiffeisen.demoapplication.converters

import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.common.operation.OperationValueResult
import ru.raiffeisen.demoapplication.dtos.UserProfileDto
import ru.raiffeisen.demoapplication.extensions.mapOperationTransform
import ru.raiffeisen.demoapplication.model.UserProfileModel

@Component
class UserProfileConverter(private val marketItemConverter: MarketItemConverter): Converter<UserProfileModel, UserProfileDto> {
    override fun convert(input: UserProfileModel): OperationValueResult<UserProfileDto> {
        val pluginsDtoResult = input.plugins.mapOperationTransform { marketItemConverter.convert(it) }

        return pluginsDtoResult.map { plugins ->
            UserProfileDto(
                firstName = input.firstName,
                lastName = input.lastName,
                plugins = plugins
            )
        }
    }
}
