package ru.tk4dmitriy.screens.closer.di

import dagger.Binds
import dagger.Module
import ru.tk4dmitriy.screens.closer.api.CloserStarter
import ru.tk4dmitriy.screens.closer.starter.CloserStarterImpl

@Module
internal interface CloserStarterModule {
    @Binds
    fun provideCloserStarter(closerStarterImpl: CloserStarterImpl): CloserStarter
}