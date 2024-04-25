package ru.tk4dmitriy.data.offers_tickets.impl

import ru.tk4dmitriy.data.offers_tickets.api.OfferTicket
import ru.tk4dmitriy.data.offers_tickets.api.OffersTicketsRepository
import ru.tk4dmitriy.data.offers_tickets.impl.localSource.OffersTicketsLocalSource
import javax.inject.Inject

internal class OffersTicketsRepositoryImpl @Inject constructor(
    private val offersTicketsLocalSource: OffersTicketsLocalSource
) : OffersTicketsRepository {
    override suspend fun getOffersTickets(): List<OfferTicket> =
        offersTicketsLocalSource.getOffersTickets()
}