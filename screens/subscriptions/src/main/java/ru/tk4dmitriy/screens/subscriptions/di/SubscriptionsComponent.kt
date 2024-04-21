package ru.tk4dmitriy.screens.subscriptions.di

import dagger.Component
import ru.tk4dmitriy.screens.subscriptions.SubscriptionsFragment
import ru.tk4dmitriy.screens.subscriptions.api.SubscriptionsScreenApi

@Component(
    dependencies = [SubscriptionsComponentDependencies::class],
    modules = [SubscriptionsStarterModule::class]
)
internal abstract class SubscriptionsComponent : SubscriptionsScreenApi {
    abstract fun inject(fragment: SubscriptionsFragment)

    companion object {
        fun initAndGet(dependencies: SubscriptionsComponentDependencies): SubscriptionsComponent {
            return DaggerSubscriptionsComponent.builder()
                .subscriptionsComponentDependencies(dependencies)
                .build()
        }
    }
}
