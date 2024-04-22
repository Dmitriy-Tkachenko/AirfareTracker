package ru.tk4dmitriy.screens.airfares.di

import dagger.Component
import ru.tk4dmitriy.core.utils.di.viewModel.ViewModelModule
import ru.tk4dmitriy.core.utils.di.scopes.ScreenScope
import ru.tk4dmitriy.screens.airfares.ui.AirfaresFragment
import ru.tk4dmitriy.screens.airfares.api.AirfaresScreenApi

@ScreenScope
@Component(
    dependencies = [AirfaresComponentDependencies::class],
    modules = [
        AirfaresStarterModule::class,
        AirfaresViewModelModule::class,
        ViewModelModule::class]
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
