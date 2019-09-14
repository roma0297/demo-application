package ru.raiffeisen.demoapplication.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import ru.raiffeisen.demoapplication.dtos.UserProfileDto
import ru.raiffeisen.demoapplication.model.UserProfile

@Component
class UserProfileConverter(val pluginConverter: PluginConverter) : Converter<UserProfile, UserProfileDto> {
    override fun convert(source: UserProfile): UserProfileDto? {
        return UserProfileDto(
                firstName = source.firstName,
                lastName = source.lastName,
                plugins = source.plugins.mapNotNull { pluginConverter.convert(it) }
        )
    }
}