package ru.raiffeisen.demoapplication.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
data class ResourceNotFoundException(
    val resourceName: String,
    val fieldName: String,
    val fieldValue: Any
) : RuntimeException("$resourceName not found with $fieldName : '$fieldValue'")
