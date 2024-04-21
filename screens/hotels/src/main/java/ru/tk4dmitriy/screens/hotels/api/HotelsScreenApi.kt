package ru.tk4dmitriy.screens.hotels.api

import ru.tk4dmitriy.module_injector.BaseAPI

interface HotelsScreenApi: BaseAPI {
    fun getHotelsStarter(): HotelsStarter
}