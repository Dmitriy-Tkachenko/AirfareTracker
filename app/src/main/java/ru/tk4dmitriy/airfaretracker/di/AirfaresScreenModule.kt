package ru.tk4dmitriy.airfaretracker.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.tk4dmitriy.data.departure_place.api.DeparturePlaceDataApi
import ru.tk4dmitriy.data.departure_place.impl.di.DeparturePlaceDataComponent
import ru.tk4dmitriy.data.departure_place.impl.di.DeparturePlaceDataComponentDependencies
import ru.tk4dmitriy.data.offers.api.OffersDataApi
import ru.tk4dmitriy.data.offers.impl.di.OffersDataComponent
import ru.tk4dmitriy.data.offers.impl.di.OffersDataComponentDependencies
import ru.tk4dmitriy.data.offers_tickets.api.OffersTicketsDataApi
import ru.tk4dmitriy.data.offers_tickets.impl.di.OffersTicketsDataComponent
import ru.tk4dmitriy.data.offers_tickets.impl.di.OffersTicketsDataComponentDependencies
import ru.tk4dmitriy.data.tickets.api.TicketsDataApi
import ru.tk4dmitriy.data.tickets.impl.di.TicketsDataComponent
import ru.tk4dmitriy.data.tickets.impl.di.TicketsDataComponentDependencies
import ru.tk4dmitriy.feature.offers.api.GetOffersFeatureCase
import ru.tk4dmitriy.features.departure_place.api.GetDeparturePlaceFeatureCase
import ru.tk4dmitriy.features.departure_place.api.SaveDeparturePlaceFeatureCase
import ru.tk4dmitriy.features.departure_place.impl.GetDeparturePlaceFeatureCaseImpl
import ru.tk4dmitriy.features.departure_place.impl.SaveDeparturePlaceFeatureCaseImpl
import ru.tk4dmitriy.features.offers.impl.GetOffersFeatureCaseImpl
import ru.tk4dmitriy.features.offers_tickets.api.GetOffersTicketsFeatureCase
import ru.tk4dmitriy.features.offers_tickets.impl.GetOffersTicketsFeatureCaseImpl
import ru.tk4dmitriy.features.tickets.api.GetTicketsFeatureCase
import ru.tk4dmitriy.features.tickets.impl.GetTicketsFeatureCaseImpl
import ru.tk4dmitriy.screens.airfares.api.AirfaresScreenApi
import ru.tk4dmitriy.screens.airfares.di.AirfaresComponentDependencies
import ru.tk4dmitriy.screens.airfares.di.AirfaresComponentHolder
import javax.inject.Singleton

@Module
class AirfaresScreenModule {

    @Provides
    fun provideDeparturePlaceDataComponentDependencies(
        context: Context
    ): DeparturePlaceDataComponentDependencies {
        return object : DeparturePlaceDataComponentDependencies {
            override fun getContext(): Context = context
        }
    }

    @Provides
    fun provideDeparturePlaceDataComponent(
        dependencies: DeparturePlaceDataComponentDependencies
    ): DeparturePlaceDataApi {
        return DeparturePlaceDataComponent.initAndGet(dependencies)
    }

    @Provides
    fun provideOffersDataComponentDependencies(
        context: Context
    ): OffersDataComponentDependencies {
        return object : OffersDataComponentDependencies {
            override fun getContext(): Context = context
        }
    }

    @Provides
    fun provideOffersDataComponent(
        dependencies: OffersDataComponentDependencies
    ): OffersDataApi {
        return OffersDataComponent.initAndGet(dependencies)
    }

    @Provides
    fun provideOffersTicketsDataComponentDependencies(
        context: Context
    ): OffersTicketsDataComponentDependencies {
        return object : OffersTicketsDataComponentDependencies {
            override fun getContext(): Context = context
        }
    }

    @Provides
    fun provideOffersTicketsDataComponent(
        dependencies: OffersTicketsDataComponentDependencies
    ): OffersTicketsDataApi {
        return OffersTicketsDataComponent.initAndGet(dependencies)
    }

    @Provides
    fun provideTicketsDataComponentDependencies(
        context: Context
    ): TicketsDataComponentDependencies {
        return object : TicketsDataComponentDependencies {
            override fun getContext(): Context = context
        }
    }

    @Provides
    fun provideTicketsDataComponent(
        dependencies: TicketsDataComponentDependencies
    ): TicketsDataApi {
        return TicketsDataComponent.initAndGet(dependencies)
    }

    @Singleton
    @Provides
    fun provideAirfaresDependencies(
        departurePlaceDataApi: DeparturePlaceDataApi,
        offersDataApi: OffersDataApi,
        offersTicketsDataApi: OffersTicketsDataApi,
        ticketsDataApi: TicketsDataApi,
    ): AirfaresComponentDependencies {
        return object : AirfaresComponentDependencies {
            override fun saveDeparturePlaceFeatureCase(): SaveDeparturePlaceFeatureCase =
                SaveDeparturePlaceFeatureCaseImpl(
                    departurePlaceDataApi.getDeparturePlaceRepository()
                )

            override fun getDeparturePlaceFeatureCase(): GetDeparturePlaceFeatureCase =
                GetDeparturePlaceFeatureCaseImpl(
                    departurePlaceDataApi.getDeparturePlaceRepository()
                )

            override fun getOffersFeatureCase(): GetOffersFeatureCase =
                GetOffersFeatureCaseImpl(
                    offersDataApi.getOffersRepository()
                )

            override fun getOffersTicketsFeatureCase(): GetOffersTicketsFeatureCase =
                GetOffersTicketsFeatureCaseImpl(
                    offersTicketsDataApi.getOffersTicketsRepository()
                )

            override fun getTicketsFeatureCase(): GetTicketsFeatureCase =
                GetTicketsFeatureCaseImpl(
                    ticketsDataApi.getTicketsRepository()
                )
        }
    }

    @Provides
    fun provideScreenAirfares(
        dependencies: AirfaresComponentDependencies
    ): AirfaresScreenApi {
        AirfaresComponentHolder.init(dependencies)
        return AirfaresComponentHolder.get()
    }
}