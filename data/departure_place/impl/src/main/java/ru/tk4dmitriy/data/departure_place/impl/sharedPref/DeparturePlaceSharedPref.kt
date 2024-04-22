package ru.tk4dmitriy.data.departure_place.impl.sharedPref

interface DeparturePlaceSharedPref {
    suspend fun saveDeparturePlace(departurePlace: String)
    suspend fun getDeparturePlace() : String
}