package ru.tk4dmitriy.airfaretracker.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.tk4dmitriy.airfaretracker.app.App
import javax.inject.Singleton

@Module
class ContextModule {
    @Singleton
    @Provides
    fun provideContext(): Context = App.appContext
}