package ru.tk4dmitriy.data.offers.impl

import ru.tk4dmitriy.data.offers.api.Offer
import ru.tk4dmitriy.data.offers.api.OffersRepository
import ru.tk4dmitriy.data.offers.impl.localSource.OffersLocalSource
import javax.inject.Inject

class OffersRepositoryImpl @Inject constructor(
    private var offersLocalSource: OffersLocalSource
) : OffersRepository {
    override suspend fun getOffers(): List<Offer> =
        offersLocalSource.getOffers()
}