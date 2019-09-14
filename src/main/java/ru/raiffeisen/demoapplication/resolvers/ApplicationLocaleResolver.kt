package ru.raiffeisen.demoapplication.resolvers

import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import ru.raiffeisen.demoapplication.repositories.UserProfileRepository
import ru.raiffeisen.demoapplication.services.UserProfileService
import java.util.Locale
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ApplicationLocaleResolver(val userService: UserProfileService,
                                val userRepository: UserProfileRepository) : SessionLocaleResolver() {

    override fun resolveLocale(request: HttpServletRequest): Locale {
        val currentUser = userService.getCurrentUser()
        return currentUser?.locale ?: LocaleContextHolder.getLocale()
    }

    override fun setLocale(request: HttpServletRequest, response: HttpServletResponse?, locale: Locale?) {
        super.setLocale(request, response, locale)

        val currentUser = userService.getCurrentUser()
        currentUser?.let { user ->
            userRepository.save(user.copy(locale = locale ?: Locale.ENGLISH))
        }
    }
}
