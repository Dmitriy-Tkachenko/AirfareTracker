package ru.tk4dmitriy.data.offers.impl.di

import android.content.Context
import dagger.Component
import ru.tk4dmitriy.core.utils.di.scopes.DataScope
import ru.tk4dmitriy.data.offers.api.OffersDataApi
import ru.tk4dmitriy.data.offers.impl.localSource.OffersLocalSource

@DataScope
@Component(dependencies = [OffersDataComponentDependencies::class], modules = [OffersDataModule::class])
abstract class OffersDataComponent : OffersDataApi {
    internal abstract fun getContext(): Context
    internal abstract fun getOfferLocalSource(): OffersLocalSource

    companion object {
        @Volatile
        private var offersDataComponent: OffersDataComponent? = null

        fun initAndGet(dependencies: OffersDataComponentDependencies): OffersDataComponent {
            if (offersDataComponent == null) {
                synchronized(OffersDataComponent::class.java) {
                    if (offersDataComponent == null) {
                        offersDataComponent = DaggerOffersDataComponent.builder()
                            .offersDataComponentDependencies(dependencies)
                            .build()
                    }
                }
            }
            return offersDataComponent!!
        }
    }
}