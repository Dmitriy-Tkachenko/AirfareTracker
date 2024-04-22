package ru.tk4dmitriy.feature.offers.api

interface GetOffersFeatureCase {
    suspend operator fun invoke(): List<Offer>
}