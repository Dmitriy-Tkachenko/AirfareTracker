package ru.tk4dmitriy.features.departure_place.impl

import ru.tk4dmitriy.data.departure_place.api.DeparturePlaceRepository
import ru.tk4dmitriy.features.departure_place.api.GetDeparturePlaceFeatureCase
import ru.tk4dmitriy.features.departure_place.api.SaveDeparturePlaceFeatureCase
import javax.inject.Inject

class GetDeparturePlaceFeatureCaseImpl @Inject constructor(
    private val departurePlaceRepository: DeparturePlaceRepository
) : GetDeparturePlaceFeatureCase {
    override suspend fun invoke(): String =
        departurePlaceRepository.getDeparturePlace()
}