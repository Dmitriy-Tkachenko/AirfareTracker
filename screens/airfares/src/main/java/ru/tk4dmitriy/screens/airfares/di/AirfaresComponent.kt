package ru.tk4dmitriy.screens.airfares.di

import dagger.Component
import ru.tk4dmitriy.screens.airfares.AirfaresFragment
import ru.tk4dmitriy.screens.airfares.api.AirfaresScreenApi

@Component(
    dependencies = [AirfaresComponentDependencies::class],
    modules = [AirfaresStarterModule::class]
)
internal abstract class AirfaresComponent : AirfaresScreenApi {
    abstract fun inject(fragment: AirfaresFragment)

    companion object {
        fun initAndGet(dependencies: AirfaresComponentDependencies): AirfaresComponent {
            return DaggerAirfaresComponent.builder()
                .airfaresComponentDependencies(dependencies)
                .build()
        }
    }
}
