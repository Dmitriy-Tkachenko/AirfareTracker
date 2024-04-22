package ru.tk4dmitriy.features.departure_place.api

interface GetDeparturePlaceFeatureCase {
    suspend operator fun invoke(): String
}