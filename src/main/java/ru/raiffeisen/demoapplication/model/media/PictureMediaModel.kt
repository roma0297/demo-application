package ru.raiffeisen.demoapplication.model.media

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("picture")
class PictureMediaModel : AbstractMediaModel()
