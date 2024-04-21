package ru.tk4dmitriy.screens.airfares.api

import ru.tk4dmitriy.module_injector.BaseAPI

interface AirfaresScreenApi: BaseAPI {
    fun getAirfaresStarter() : AirfaresStarter
}