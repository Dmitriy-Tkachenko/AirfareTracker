package ru.tk4dmitriy.screens.closer.api

import ru.tk4dmitriy.module_injector.BaseAPI

interface CloserScreenApi: BaseAPI {
    fun getCloserStarter(): CloserStarter
}