package ru.tk4dmitriy.screens.subscriptions.api

import ru.tk4dmitriy.module_injector.BaseAPI

interface SubscriptionsScreenApi: BaseAPI {
    fun getSubscriptionsStarter() : SubscriptionsStarter
}