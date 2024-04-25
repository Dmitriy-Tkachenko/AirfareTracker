package ru.tk4dmitriy.data.offers_tickets.impl.di

import android.content.Context
import dagger.Component
import ru.tk4dmitriy.core.utils.di.scopes.DataScope
import ru.tk4dmitriy.data.offers_tickets.api.OffersTicketsDataApi
import ru.tk4dmitriy.data.offers_tickets.impl.localSource.OffersTicketsLocalSource

@DataScope
@Component(dependencies = [OffersTicketsDataComponentDependencies::class], modules = [OffersDataModule::class])
abstract class OffersTicketsDataComponent : OffersTicketsDataApi {
    internal abstract fun getContext(): Context
    internal abstract fun getOffersTicketsLocalSource(): OffersTicketsLocalSource

    companion object {
        @Volatile
        private var offersTicketsDataComponent: OffersTicketsDataComponent? = null

        fun initAndGet(dependencies: OffersTicketsDataComponentDependencies): OffersTicketsDataComponent {
            if (offersTicketsDataComponent == null) {
                synchronized(OffersTicketsDataComponent::class.java) {
                    if (offersTicketsDataComponent == null) {
                        offersTicketsDataComponent = DaggerOffersTicketsDataComponent.builder()
                            .offersTicketsDataComponentDependencies(dependencies)
                            .build()
                    }
                }
            }
            return offersTicketsDataComponent!!
        }
    }
}