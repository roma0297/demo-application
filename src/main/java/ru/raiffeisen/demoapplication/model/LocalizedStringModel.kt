package ru.raiffeisen.demoapplication.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "localized_strings")
class LocalizedStringModel(
    @Column(name = "english_translation")
    val englishTranslation: String = "",
    @Column(name = "russian_translation")
    val russianTranslation: String = ""
) : AbstractJpaPersistable<Long>()
