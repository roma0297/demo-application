package ru.raiffeisen.demoapplication.model.media

import ru.raiffeisen.demoapplication.model.AbstractJpaPersistable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.DiscriminatorColumn
import javax.persistence.Entity
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.Table

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@Table(name = "translatable_items")
abstract class AbstractMediaModel(
    @Column(name = "fileSystemPath")
    open val fileSystemPath: String = "",
    @Column(name = "createDateTime")
    open val createDateTime: LocalDateTime = LocalDateTime.now()
) : AbstractJpaPersistable<Long>()
