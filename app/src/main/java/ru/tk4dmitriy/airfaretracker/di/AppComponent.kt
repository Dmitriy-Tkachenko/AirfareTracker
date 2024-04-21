package ru.tk4dmitriy.airfaretracker.di

import dagger.Component
import ru.tk4dmitriy.airfaretracker.MainActivity
import javax.inject.Singleton

@Component(modules = [
    ContextModule::class,
    AirfaresScreenModule::class,
    HotelsScreenModule::class,
    CloserScreenModule::class,
    SubscriptionsScreenModule::class,
    ProfileScreenModule::class
])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }
}