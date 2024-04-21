package ru.tk4dmitriy.airfaretracker.di

import dagger.Module
import dagger.Provides
import ru.tk4dmitriy.screens.hotels.api.HotelsScreenApi
import ru.tk4dmitriy.screens.hotels.di.HotelsComponentDependencies
import ru.tk4dmitriy.screens.hotels.di.HotelsComponentHolder
import javax.inject.Singleton

@Module
class HotelsScreenModule {
    @Singleton
    @Provides
    fun provideHotelsDependencies(): HotelsComponentDependencies {
        return object : HotelsComponentDependencies { }
    }

    @Provides
    fun provideScreenHotels(dependencies: HotelsComponentDependencies): HotelsScreenApi {
        HotelsComponentHolder.init(dependencies)
        return HotelsComponentHolder.get()
    }
}