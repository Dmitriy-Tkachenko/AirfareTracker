package ru.tk4dmitriy.airfaretracker.di

import dagger.Module
import dagger.Provides
import ru.tk4dmitriy.screens.profile.api.ProfileScreenApi
import ru.tk4dmitriy.screens.profile.di.ProfileComponentDependencies
import ru.tk4dmitriy.screens.profile.di.ProfileComponentHolder
import javax.inject.Singleton

@Module
class ProfileScreenModule {
    @Singleton
    @Provides
    fun provideProfileDependencies(): ProfileComponentDependencies {
        return object : ProfileComponentDependencies { }
    }

    @Provides
    fun provideScreenProfile(dependencies: ProfileComponentDependencies): ProfileScreenApi {
        ProfileComponentHolder.init(dependencies)
        return ProfileComponentHolder.get()
    }
}