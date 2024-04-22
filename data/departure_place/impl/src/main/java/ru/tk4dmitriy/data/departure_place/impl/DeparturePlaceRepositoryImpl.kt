package ru.tk4dmitriy.data.departure_place.impl

import ru.tk4dmitriy.data.departure_place.api.DeparturePlaceRepository
import ru.tk4dmitriy.data.departure_place.impl.sharedPref.DeparturePlaceSharedPref
import javax.inject.Inject

class DeparturePlaceRepositoryImpl @Inject constructor(
    private val departurePlaceSharedPref: DeparturePlaceSharedPref
) : DeparturePlaceRepository {
    override suspend fun saveDeparturePlace(departurePlace: String) {
        departurePlaceSharedPref.saveDeparturePlace(departurePlace)
    }

    override suspend fun getDeparturePlace(): String =
        departurePlaceSharedPref.getDeparturePlace()
}