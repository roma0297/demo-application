package ru.raiffeisen.demoapplication.model

import ru.raiffeisen.demoapplication.model.localization.LocalizedStringModel
import javax.persistence.Entity
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "entrepreneur_application_statuses")
class EntrepreneurApplicationStatusModel(
    val ordinalNumber: Int = 0,
    @OneToOne
    val title: LocalizedStringModel? = null,
    @OneToOne
    val description: LocalizedStringModel? = null,
    @OneToOne
    val tooltipText: LocalizedStringModel? = null
) : AbstractJpaPersistable<Long>()