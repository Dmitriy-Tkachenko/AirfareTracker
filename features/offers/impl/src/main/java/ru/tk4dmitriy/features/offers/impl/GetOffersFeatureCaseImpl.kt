package ru.tk4dmitriy.features.offers.impl

import ru.tk4dmitriy.data.offers.api.OffersRepository
import ru.tk4dmitriy.feature.offers.api.GetOffersFeatureCase
import ru.tk4dmitriy.feature.offers.api.Offer
import javax.inject.Inject

class GetOffersFeatureCaseImpl @Inject constructor(
    private val offersRepository: OffersRepository
) : GetOffersFeatureCase {
    override suspend fun invoke(): List<Offer> =
        offersRepository.getOffers().map {
            Offer(
                id = it.id,
                title = it.title,
                town = it.town,
                price = it.price,
                imgPreview = it.imgPreview
            )
        }
}