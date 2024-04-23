package ru.tk4dmitriy.data.offers.impl.localSource.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Response(
    @SerialName("offers") val offers: List<Offer>
)

@Serializable
internal data class Offer(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("town") val town: String,
    @SerialName("price") val price: Price
)

@Serializable
internal data class Price(
    @SerialName("value") val value: Int
)