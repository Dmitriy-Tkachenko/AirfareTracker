package ru.tk4dmitriy.screens.hotels.di

import dagger.Binds
import dagger.Module
import ru.tk4dmitriy.screens.hotels.api.HotelsStarter
import ru.tk4dmitriy.screens.hotels.starter.HotelsStarterImpl

@Module
internal interface HotelsStarterModule {
    @Binds
    fun provideHotelsStarter(hotelsStarterImpl: HotelsStarterImpl): HotelsStarter
}