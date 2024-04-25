package ru.tk4dmitriy.data.tickets.impl.di

import android.content.Context
import dagger.Component
import ru.tk4dmitriy.core.utils.di.scopes.DataScope
import ru.tk4dmitriy.data.tickets.api.TicketsDataApi
import ru.tk4dmitriy.data.tickets.impl.localSource.TicketsLocalSource

@DataScope
@Component(dependencies = [TicketsDataComponentDependencies::class], modules = [TicketsDataModule::class])
abstract class TicketsDataComponent : TicketsDataApi {
    internal abstract fun getContext(): Context
    internal abstract fun getTicketsLocalSource(): TicketsLocalSource

    companion object {
        @Volatile
        private var ticketsDataComponent: TicketsDataComponent? = null

        fun initAndGet(dependencies: TicketsDataComponentDependencies): TicketsDataComponent {
            if (ticketsDataComponent == null) {
                synchronized(TicketsDataComponent::class.java) {
                    if (ticketsDataComponent == null) {
                        ticketsDataComponent = DaggerTicketsDataComponent.builder()
                            .ticketsDataComponentDependencies(dependencies)
                            .build()
                    }
                }
            }
            return ticketsDataComponent!!
        }
    }
}