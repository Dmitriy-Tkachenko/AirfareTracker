package ru.tk4dmitriy.data.tickets.api

class Ticket(
    val id: Int,
    val badge: String?,
    val price: Int,
    val departureDate: String,
    val departureAirport: String,
    val arrivalDate: String,
    val arrivalAirport: String,
    val hasTransfer: Boolean
)