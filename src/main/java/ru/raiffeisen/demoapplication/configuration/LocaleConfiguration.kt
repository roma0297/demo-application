package ru.raiffeisen.demoapplication.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import ru.raiffeisen.demoapplication.repositories.UserProfileRepository
import ru.raiffeisen.demoapplication.resolvers.ApplicationLocaleResolver
import ru.raiffeisen.demoapplication.services.UserProfileService
import ru.raiffeisen.demoapplication.services.UserService

@Configuration
class LocaleConfiguration(
    val userProfileService: UserProfileService,
    val userProfileRepository: UserProfileRepository
) : WebMvcConfigurer {

    @Bean
    fun localeResolver(): LocaleResolver {
        return ApplicationLocaleResolver(userProfileService, userProfileRepository)
    }

    @Bean
    fun localeChangeInterceptor(): LocaleChangeInterceptor {
        val localeChangeInterceptor = LocaleChangeInterceptor()
        localeChangeInterceptor.paramName = "lang"
        return localeChangeInterceptor
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(localeChangeInterceptor())
    }
}