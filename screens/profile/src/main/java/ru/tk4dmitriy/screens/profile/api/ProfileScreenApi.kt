package ru.tk4dmitriy.screens.profile.api

import ru.tk4dmitriy.module_injector.BaseAPI

interface ProfileScreenApi: BaseAPI {
    fun getTag(): String = "PROFILE_FRAGMENT"
}