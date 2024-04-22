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
import ru.tk4dmitriy.feature.offers.api.GetOffersFeatureCase
import ru.tk4dmitriy.features.departure_place.api.GetDeparturePlaceFeatureCase
import ru.tk4dmitriy.features.departure_place.api.SaveDeparturePlaceFeatureCase
import ru.tk4dmitriy.features.departure_place.impl.GetDeparturePlaceFeatureCaseImpl
import ru.tk4dmitriy.features.departure_place.impl.SaveDeparturePlaceFeatureCaseImpl
import ru.tk4dmitriy.features.offers.impl.GetOffersFeatureCaseImpl
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

    @Singleton
    @Provides
    fun provideAirfaresDependencies(
        departurePlaceDataApi: DeparturePlaceDataApi,
        offersDataApi: OffersDataApi
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