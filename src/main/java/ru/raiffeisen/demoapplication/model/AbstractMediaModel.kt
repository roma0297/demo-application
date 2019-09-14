package ru.raiffeisen.demoapplication.model

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
    val fileSystemPath: String = "",
    @Column(name = "createDateTime")
    val createDateTime: LocalDateTime = LocalDateTime.now()
) : AbstractJpaPersistable<Long>()