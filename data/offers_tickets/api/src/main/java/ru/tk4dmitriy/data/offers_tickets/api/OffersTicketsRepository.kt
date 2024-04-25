package ru.tk4dmitriy.data.offers_tickets.api

interface OffersTicketsRepository {
    suspend fun getOffersTickets(): List<OfferTicket>
}