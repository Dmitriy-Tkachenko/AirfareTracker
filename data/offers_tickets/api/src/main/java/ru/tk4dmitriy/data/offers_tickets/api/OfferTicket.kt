package ru.tk4dmitriy.data.offers_tickets.api

class OfferTicket(
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: Int
)