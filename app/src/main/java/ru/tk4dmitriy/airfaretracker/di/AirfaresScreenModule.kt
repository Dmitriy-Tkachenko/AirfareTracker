package ru.tk4dmitriy.airfaretracker.di

import dagger.Module
import dagger.Provides
import ru.tk4dmitriy.screens.airfares.api.AirfaresScreenApi
import ru.tk4dmitriy.screens.airfares.di.AirfaresComponentDependencies
import ru.tk4dmitriy.screens.airfares.di.AirfaresComponentHolder
import javax.inject.Singleton

@Module
class AirfaresScreenModule {
    @Singleton
    @Provides
    fun provideAirfaresDependencies(): AirfaresComponentDependencies {
        return object : AirfaresComponentDependencies { }
    }

    @Provides
    fun provideScreenAirfares(dependencies: AirfaresComponentDependencies): AirfaresScreenApi {
        AirfaresComponentHolder.init(dependencies)
        return AirfaresComponentHolder.get()
    }
}