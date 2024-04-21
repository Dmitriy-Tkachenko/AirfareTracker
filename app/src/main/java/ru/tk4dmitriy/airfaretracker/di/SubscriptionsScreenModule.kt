package ru.tk4dmitriy.airfaretracker.di

import dagger.Module
import dagger.Provides
import ru.tk4dmitriy.screens.subscriptions.api.SubscriptionsScreenApi
import ru.tk4dmitriy.screens.subscriptions.di.SubscriptionsComponentDependencies
import ru.tk4dmitriy.screens.subscriptions.di.SubscriptionsComponentHolder
import javax.inject.Singleton

@Module
class SubscriptionsScreenModule {
    @Singleton
    @Provides
    fun provideSubscriptionsDependencies(): SubscriptionsComponentDependencies {
        return object : SubscriptionsComponentDependencies { }
    }

    @Provides
    fun provideScreenSubscriptions(dependencies: SubscriptionsComponentDependencies): SubscriptionsScreenApi {
        SubscriptionsComponentHolder.init(dependencies)
        return SubscriptionsComponentHolder.get()
    }
}