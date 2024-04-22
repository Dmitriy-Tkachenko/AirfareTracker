package ru.tk4dmitriy.data.departure_place.impl.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.tk4dmitriy.data.departure_place.api.DeparturePlaceRepository
import ru.tk4dmitriy.data.departure_place.impl.DeparturePlaceRepositoryImpl
import ru.tk4dmitriy.data.departure_place.impl.sharedPref.DeparturePlaceSharedPref
import ru.tk4dmitriy.data.departure_place.impl.sharedPref.DeparturePlaceSharedPrefImpl

@Module
internal class DeparturePlaceDataModule {
    @Provides
    fun provideDeparturePlaceSharedPref(context: Context): DeparturePlaceSharedPref =
        DeparturePlaceSharedPrefImpl(context)

    @Provides
    fun provideDeparturePlaceRepository(departurePlaceSharedPref: DeparturePlaceSharedPref): DeparturePlaceRepository =
        DeparturePlaceRepositoryImpl(departurePlaceSharedPref)
}