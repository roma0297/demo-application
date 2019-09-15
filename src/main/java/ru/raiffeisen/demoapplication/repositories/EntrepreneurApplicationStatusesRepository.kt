package ru.raiffeisen.demoapplication.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.raiffeisen.demoapplication.model.EntrepreneurApplicationStatusModel

@Repository
interface EntrepreneurApplicationStatusesRepository : CrudRepository<EntrepreneurApplicationStatusModel, Long>