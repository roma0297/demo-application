package ru.raiffeisen.demoapplication.services

import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Service
import ru.raiffeisen.demoapplication.model.LocalizedStringModel
import java.util.Locale

@Service
class LocalizationService {

    fun localize(localizedString: LocalizedStringModel?): String {
        return if (Locale.ENGLISH == LocaleContextHolder.getLocale()) {
            localizedString?.englishTranslation ?: ""
        } else {
            localizedString?.russianTranslation ?: ""
        }
    }
}
