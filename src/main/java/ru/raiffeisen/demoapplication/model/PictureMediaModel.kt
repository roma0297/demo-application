package ru.raiffeisen.demoapplication.model

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("picture")
class PictureMediaModel : AbstractMediaModel()
