package ru.tk4dmitriy.screens.airfares.di

import dagger.Component
import ru.tk4dmitriy.core.utils.di.viewModel.ViewModelModule
import ru.tk4dmitriy.core.utils.di.scopes.ScreenScope
import ru.tk4dmitriy.screens.airfares.ui.fragments.AirfaresFragment
import ru.tk4dmitriy.screens.airfares.api.AirfaresScreenApi
import ru.tk4dmitriy.screens.airfares.ui.fragments.ArrivalDialogFragment
import ru.tk4dmitriy.screens.airfares.ui.fragments.OffersTicketsFragment

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
    abstract fun inject(dialogFragment: ArrivalDialogFragment)
    abstract fun inject(offersTicketsFragment: OffersTicketsFragment)

    companion object {
        fun initAndGet(dependencies: AirfaresComponentDependencies): AirfaresComponent {
            return DaggerAirfaresComponent.builder()
                .airfaresComponentDependencies(dependencies)
                .build()
        }
    }
}
