package ru.tk4dmitriy.data.offers_tickets.impl.localSource

import ru.tk4dmitriy.data.offers_tickets.api.OfferTicket

internal interface OffersTicketsLocalSource {
    suspend fun getOffersTickets() : List<OfferTicket>
}