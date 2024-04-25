package ru.tk4dmitriy.data.tickets.impl.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.tk4dmitriy.data.tickets.api.TicketsRepository
import ru.tk4dmitriy.data.tickets.impl.TicketsRepositoryImpl
import ru.tk4dmitriy.data.tickets.impl.localSource.TicketsLocalSource
import ru.tk4dmitriy.data.tickets.impl.localSource.TicketsLocalSourceImpl

@Module
internal class TicketsDataModule {
    @Provides
    fun provideTicketsLocalSource(context: Context): TicketsLocalSource =
        TicketsLocalSourceImpl(context)

    @Provides
    fun provideTicketsRepository(ticketsLocalSource: TicketsLocalSource): TicketsRepository =
        TicketsRepositoryImpl(ticketsLocalSource)
}