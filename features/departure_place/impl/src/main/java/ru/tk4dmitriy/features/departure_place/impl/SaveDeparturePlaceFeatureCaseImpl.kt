package ru.tk4dmitriy.features.departure_place.impl

import ru.tk4dmitriy.data.departure_place.api.DeparturePlaceRepository
import ru.tk4dmitriy.features.departure_place.api.SaveDeparturePlaceFeatureCase
import javax.inject.Inject

class SaveDeparturePlaceFeatureCaseImpl @Inject constructor(
    private val departurePlaceRepository: DeparturePlaceRepository
) : SaveDeparturePlaceFeatureCase {
    override suspend fun invoke(departurePlace: String) {
        departurePlaceRepository.saveDeparturePlace(departurePlace)
    }
}