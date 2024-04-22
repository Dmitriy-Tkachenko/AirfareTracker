package ru.tk4dmitriy.data.departure_place.api

interface DeparturePlaceRepository {
    suspend fun saveDeparturePlace(departurePlace: String)
    suspend fun getDeparturePlace() : String
}