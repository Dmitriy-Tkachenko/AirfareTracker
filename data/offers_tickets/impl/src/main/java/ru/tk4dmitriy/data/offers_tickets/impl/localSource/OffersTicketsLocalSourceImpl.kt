package ru.tk4dmitriy.data.offers_tickets.impl.localSource

import android.content.Context
import kotlinx.serialization.json.Json
import ru.tk4dmitriy.core.utils.Utils.readJsonFromAssets
import ru.tk4dmitriy.data.offers_tickets.api.OfferTicket
import ru.tk4dmitriy.data.offers_tickets.impl.localSource.models.Response
import javax.inject.Inject

private const val FILE_NAME = "offers_tickets.json"

internal class OffersTicketsLocalSourceImpl @Inject constructor(
    private val context: Context
) : OffersTicketsLocalSource {
    override suspend fun getOffersTickets(): List<OfferTicket> {
        val json = context.assets.readJsonFromAssets(FILE_NAME)
        val response = Json.decodeFromString<Response>(json)
        return response.mapResponseToOffersTicketsData()
    }

    private fun Response.mapResponseToOffersTicketsData(): List<OfferTicket> = offersTickets.map {
        OfferTicket(
            id = it.id,
            title = it.title,
            timeRange = it.timeRange,
            price = it.price.value,
        )
    }
}