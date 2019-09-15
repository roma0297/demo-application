package ru.raiffeisen.demoapplication.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import ru.raiffeisen.demoapplication.repositories.UserProfileRepository
import ru.raiffeisen.demoapplication.services.UserContextService
import ru.raiffeisen.demoapplication.services.resolvers.ApplicationLocaleResolver

@Configuration
class LocaleConfiguration(
    private val userProfileRepository: UserProfileRepository,
    private val userContextService: UserContextService
) : WebMvcConfigurer {

    @Bean
    fun localeResolver(): LocaleResolver {
        return ApplicationLocaleResolver(userProfileRepository, userContextService)
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
