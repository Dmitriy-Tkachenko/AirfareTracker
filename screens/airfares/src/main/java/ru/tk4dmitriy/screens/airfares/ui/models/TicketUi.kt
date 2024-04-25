package ru.tk4dmitriy.screens.airfares.ui.models

class TicketUi(
    val id: Int,
    val badge: String?,
    val price: String,
    val departureTime: String,
    val departureAirport: String,
    val arrivalTime: String,
    val arrivalAirport: String,
    val hasTransfer: Boolean,
    val travelTime: String
)