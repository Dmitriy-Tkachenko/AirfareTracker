package ru.tk4dmitriy.screens.airfares.di

import dagger.Binds
import dagger.Module
import ru.tk4dmitriy.screens.airfares.api.AirfaresStarter
import ru.tk4dmitriy.screens.airfares.starter.AirfaresStarterImpl

@Module
interface AirfaresStarterModule {
    @Binds
    fun provideAirfaresStarter(airfaresStarterImpl: AirfaresStarterImpl): AirfaresStarter
}