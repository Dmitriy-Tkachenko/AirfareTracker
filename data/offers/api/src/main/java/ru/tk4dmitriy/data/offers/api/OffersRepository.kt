package ru.tk4dmitriy.data.offers.api

interface OffersRepository {
    suspend fun getOffers(): List<Offer>
}