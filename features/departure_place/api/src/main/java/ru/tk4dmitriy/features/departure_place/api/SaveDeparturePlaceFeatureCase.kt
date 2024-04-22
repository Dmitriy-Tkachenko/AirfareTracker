package ru.tk4dmitriy.features.departure_place.api

interface SaveDeparturePlaceFeatureCase {
   suspend operator fun invoke(departurePlace: String)
}