package ru.tk4dmitriy.features.offers_tickets.api

interface GetOffersTicketsFeatureCase {
    suspend operator fun invoke(): List<OfferTicket>
}