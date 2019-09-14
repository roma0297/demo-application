package ru.raiffeisen.demoapplication.common.command

import ru.raiffeisen.demoapplication.common.operation.OperationResult

abstract class NoInputCommand<out R : OperationResult> : Command<Unit, R> {
    override fun process(input: Unit): R = doProcess()

    abstract fun doProcess(): R
}
