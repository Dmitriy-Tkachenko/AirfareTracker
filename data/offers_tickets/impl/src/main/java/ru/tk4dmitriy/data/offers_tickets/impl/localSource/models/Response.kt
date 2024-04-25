package ru.tk4dmitriy.data.offers_tickets.impl.localSource.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Response(
    @SerialName("tickets_offers") val offersTickets: List<OfferTicket>
)

@Serializable
internal data class OfferTicket(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("time_range") val timeRange: List<String>,
    @SerialName("price") val price: Price
)

@Serializable
internal data class Price(
    @SerialName("value") val value: Int
)