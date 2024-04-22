package ru.tk4dmitriy.data.departure_place.impl.di

import android.content.Context
import dagger.Component
import ru.tk4dmitriy.core.utils.di.scopes.DataScope
import ru.tk4dmitriy.data.departure_place.api.DeparturePlaceDataApi
import ru.tk4dmitriy.data.departure_place.impl.sharedPref.DeparturePlaceSharedPref

@DataScope
@Component(dependencies = [DeparturePlaceDataComponentDependencies::class], modules = [DeparturePlaceDataModule::class])
abstract class DeparturePlaceDataComponent : DeparturePlaceDataApi {
    internal abstract fun getContext(): Context
    internal abstract fun getDeparturePlaceSharedPref(): DeparturePlaceSharedPref

    companion object {
        @Volatile
        private var departurePlaceDataComponent: DeparturePlaceDataComponent? = null

        fun initAndGet(dependencies: DeparturePlaceDataComponentDependencies): DeparturePlaceDataComponent {
            if (departurePlaceDataComponent == null) {
                synchronized(DeparturePlaceDataComponent::class.java) {
                    if (departurePlaceDataComponent == null) {
                        departurePlaceDataComponent = DaggerDeparturePlaceDataComponent.builder()
                            .departurePlaceDataComponentDependencies(dependencies)
                            .build()
                    }
                }
            }
            return departurePlaceDataComponent!!
        }
    }
}