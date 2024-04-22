package ru.tk4dmitriy.screens.airfares.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.tk4dmitriy.core.utils.di.viewModel.ViewModelKey
import ru.tk4dmitriy.screens.airfares.ui.AirfaresViewModel

@Module
internal interface AirfaresViewModelModule {
    @Binds
    @[IntoMap ViewModelKey(AirfaresViewModel::class)]
    fun provideProductsViewModel(airfaresViewModel: AirfaresViewModel): ViewModel
}