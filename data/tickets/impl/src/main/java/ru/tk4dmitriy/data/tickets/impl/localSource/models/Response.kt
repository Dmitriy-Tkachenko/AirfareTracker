package ru.tk4dmitriy.data.tickets.impl.localSource.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Response(
    @SerialName("tickets") val offersTickets: List<Ticket>
)

@Serializable
internal data class Ticket(
    @SerialName("id") val id: Int,
    @SerialName("badge") val badge: String? = null,
    @SerialName("price") val price: Price,
    @SerialName("departure") val departure: Departure,
    @SerialName("arrival") val arrival: Arrival,
    @SerialName("has_transfer") val hasTransfer: Boolean,
)

@Serializable
internal data class Price(
    @SerialName("value") val value: Int
)

@Serializable
internal data class Departure(
    @SerialName("date") val date: String,
    @SerialName("airport") val airport: String
)

@Serializable
internal data class Arrival(
    @SerialName("date") val date: String,
    @SerialName("airport") val airport: String
)