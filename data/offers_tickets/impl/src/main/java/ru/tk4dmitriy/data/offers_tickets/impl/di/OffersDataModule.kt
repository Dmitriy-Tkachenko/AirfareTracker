package ru.tk4dmitriy.data.offers_tickets.impl.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.tk4dmitriy.data.offers_tickets.api.OffersTicketsRepository
import ru.tk4dmitriy.data.offers_tickets.impl.OffersTicketsRepositoryImpl
import ru.tk4dmitriy.data.offers_tickets.impl.localSource.OffersTicketsLocalSource
import ru.tk4dmitriy.data.offers_tickets.impl.localSource.OffersTicketsLocalSourceImpl

@Module
internal class OffersDataModule {
    @Provides
    fun provideOffersTicketsLocalSource(context: Context): OffersTicketsLocalSource =
        OffersTicketsLocalSourceImpl(context)

    @Provides
    fun provideOffersTicketsRepository(offersTicketsLocalSource: OffersTicketsLocalSource): OffersTicketsRepository =
        OffersTicketsRepositoryImpl(offersTicketsLocalSource)
}