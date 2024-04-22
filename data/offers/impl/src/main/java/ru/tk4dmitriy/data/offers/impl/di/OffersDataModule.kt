package ru.tk4dmitriy.data.offers.impl.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.tk4dmitriy.data.offers.api.OffersRepository
import ru.tk4dmitriy.data.offers.impl.OffersRepositoryImpl
import ru.tk4dmitriy.data.offers.impl.localSource.OffersLocalSource
import ru.tk4dmitriy.data.offers.impl.localSource.OffersLocalSourceImpl

@Module
internal class OffersDataModule {
    @Provides
    fun provideOffersLocalSource(context: Context): OffersLocalSource =
        OffersLocalSourceImpl(context)

    @Provides
    fun provideOffersRepository(offersLocalSource: OffersLocalSource): OffersRepository =
        OffersRepositoryImpl(offersLocalSource)
}