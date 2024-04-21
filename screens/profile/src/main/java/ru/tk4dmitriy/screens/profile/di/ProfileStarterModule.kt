package ru.tk4dmitriy.screens.profile.di

import dagger.Binds
import dagger.Module
import ru.tk4dmitriy.screens.profile.api.ProfileStarter
import ru.tk4dmitriy.screens.profile.starter.ProfileStarterImpl

@Module
interface ProfileStarterModule {
    @Binds
    fun provideProfileStarter(profileStarterImpl: ProfileStarterImpl): ProfileStarter
}