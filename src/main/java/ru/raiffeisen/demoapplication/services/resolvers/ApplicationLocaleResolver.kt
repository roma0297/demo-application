package ru.raiffeisen.demoapplication.services.resolvers

import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import ru.raiffeisen.demoapplication.repositories.UserProfileRepository
import ru.raiffeisen.demoapplication.security.service.CustomUserDetailsService
import java.util.Locale
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ApplicationLocaleResolver(
    private val userRepository: UserProfileRepository,
    //private val userContextService: UserContextService
    private val userContextService: CustomUserDetailsService
) : SessionLocaleResolver() {

    override fun resolveLocale(request: HttpServletRequest): Locale {
        val currentUser = userContextService.getCurrentUser()
        val defaultLocale = LocaleContextHolder.getLocale()
        currentUser.ifFailure { return defaultLocale }
        return currentUser.value?.locale ?: defaultLocale
    }

    override fun setLocale(
        request: HttpServletRequest,
        response: HttpServletResponse?,
        locale: Locale?
    ) {
        super.setLocale(request, response, locale)

        val currentUser = userContextService.getCurrentUser()
        currentUser.value?.let { user ->
            userRepository.save(user.copy(locale = locale ?: Locale.ENGLISH))
        }
    }
}
