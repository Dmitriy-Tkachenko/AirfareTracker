package ru.tk4dmitriy.screens.subscriptions.di

import ru.tk4dmitriy.module_injector.ComponentHolder
import ru.tk4dmitriy.screens.subscriptions.api.SubscriptionsScreenApi

object SubscriptionsComponentHolder : ComponentHolder<SubscriptionsScreenApi, SubscriptionsComponentDependencies> {
    private var subscriptionsComponent: SubscriptionsComponent? = null

    override fun init(dependencies: SubscriptionsComponentDependencies) {
        if (subscriptionsComponent == null) {
            synchronized(SubscriptionsComponentHolder::class.java) {
                if (subscriptionsComponent == null) {
                    subscriptionsComponent = SubscriptionsComponent.initAndGet(dependencies = dependencies)
                }
            }
        }
    }

    override fun get(): SubscriptionsScreenApi = getComponent()

    internal fun getComponent(): SubscriptionsComponent {
        checkNotNull(subscriptionsComponent) { "SubscriptionsComponent was not initialized!" }
        return subscriptionsComponent!!
    }

    override fun reset() {
        subscriptionsComponent = null
    }
}