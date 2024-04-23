package ru.tk4dmitriy.data.offers.impl.localSource

import ru.tk4dmitriy.data.offers.api.Offer

internal interface OffersLocalSource {
    suspend fun getOffers() : List<Offer>
}