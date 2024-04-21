package ru.tk4dmitriy.screens.subscriptions.di

import dagger.Binds
import dagger.Module
import ru.tk4dmitriy.screens.subscriptions.api.SubscriptionsStarter
import ru.tk4dmitriy.screens.subscriptions.starter.SubscriptionsStarterImpl

@Module
interface SubscriptionsStarterModule {
    @Binds
    fun provideSubscriptionsStarter(subscriptionsStarterImpl: SubscriptionsStarterImpl): SubscriptionsStarter
}