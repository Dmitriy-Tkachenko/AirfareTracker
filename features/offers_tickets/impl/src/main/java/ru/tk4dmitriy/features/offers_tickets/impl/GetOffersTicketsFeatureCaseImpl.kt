package ru.tk4dmitriy.features.offers_tickets.impl

import ru.tk4dmitriy.data.offers_tickets.api.OffersTicketsRepository
import ru.tk4dmitriy.features.offers_tickets.api.GetOffersTicketsFeatureCase
import ru.tk4dmitriy.features.offers_tickets.api.OfferTicket
import javax.inject.Inject

class GetOffersTicketsFeatureCaseImpl @Inject constructor(
    private var offersTicketsRepository: OffersTicketsRepository
): GetOffersTicketsFeatureCase {
    override suspend fun invoke(): List<OfferTicket> =
        offersTicketsRepository.getOffersTickets().map {
            OfferTicket(
                id = it.id,
                title = it.title,
                timeRange = it.timeRange,
                price = it.price,
            )
        }
}