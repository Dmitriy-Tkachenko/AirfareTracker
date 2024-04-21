package ru.tk4dmitriy.airfaretracker.di

import dagger.Module
import dagger.Provides
import ru.tk4dmitriy.screens.closer.api.CloserScreenApi
import ru.tk4dmitriy.screens.closer.di.CloserComponentDependencies
import ru.tk4dmitriy.screens.closer.di.CloserComponentHolder
import javax.inject.Singleton

@Module
class CloserScreenModule {
    @Singleton
    @Provides
    fun provideCloserDependencies(): CloserComponentDependencies {
        return object : CloserComponentDependencies { }
    }

    @Provides
    fun provideScreenCloser(dependencies: CloserComponentDependencies): CloserScreenApi {
        CloserComponentHolder.init(dependencies)
        return CloserComponentHolder.get()
    }
}