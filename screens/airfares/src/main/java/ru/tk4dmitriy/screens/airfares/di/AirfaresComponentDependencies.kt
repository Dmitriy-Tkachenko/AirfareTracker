package ru.tk4dmitriy.screens.airfares.di

import ru.tk4dmitriy.feature.offers.api.GetOffersFeatureCase
import ru.tk4dmitriy.features.departure_place.api.GetDeparturePlaceFeatureCase
import ru.tk4dmitriy.features.departure_place.api.SaveDeparturePlaceFeatureCase
import ru.tk4dmitriy.features.offers_tickets.api.GetOffersTicketsFeatureCase
import ru.tk4dmitriy.features.tickets.api.GetTicketsFeatureCase
import ru.tk4dmitriy.module_injector.BaseDependencies

interface AirfaresComponentDependencies : BaseDependencies {
    fun saveDeparturePlaceFeatureCase() : SaveDeparturePlaceFeatureCase
    fun getDeparturePlaceFeatureCase() : GetDeparturePlaceFeatureCase
    fun getOffersFeatureCase() : GetOffersFeatureCase
    fun getOffersTicketsFeatureCase() : GetOffersTicketsFeatureCase
    fun getTicketsFeatureCase() : GetTicketsFeatureCase
}