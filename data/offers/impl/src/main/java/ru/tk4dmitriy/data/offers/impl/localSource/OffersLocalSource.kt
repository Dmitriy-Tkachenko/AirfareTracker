package ru.tk4dmitriy.data.offers.impl.localSource

import ru.tk4dmitriy.data.offers.api.Offer

interface OffersLocalSource {
    suspend fun getOffers() : List<Offer>
}