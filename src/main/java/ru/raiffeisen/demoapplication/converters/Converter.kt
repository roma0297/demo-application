package ru.raiffeisen.demoapplication.converters

import ru.raiffeisen.demoapplication.common.operation.OperationValueResult

interface Converter<in T, R> {
    fun convert(input: T): OperationValueResult<R>
}
