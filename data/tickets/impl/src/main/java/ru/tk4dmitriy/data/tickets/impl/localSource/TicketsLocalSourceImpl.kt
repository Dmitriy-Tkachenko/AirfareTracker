package ru.tk4dmitriy.data.tickets.impl.localSource

import android.content.Context
import kotlinx.serialization.json.Json
import ru.tk4dmitriy.core.utils.Utils.readJsonFromAssets
import ru.tk4dmitriy.data.tickets.api.Ticket
import ru.tk4dmitriy.data.tickets.impl.localSource.models.Response
import javax.inject.Inject

private const val FILE_NAME = "tickets.json"

internal class TicketsLocalSourceImpl @Inject constructor(
    private val context: Context
) : TicketsLocalSource {
    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun getTickets(): List<Ticket> {
        val json = context.assets.readJsonFromAssets(FILE_NAME)
        val response = this.json.decodeFromString<Response>(json)
        return response.mapResponseToTicketsData()
    }


    private fun Response.mapResponseToTicketsData(): List<Ticket> = offersTickets.map {
        Ticket(
            id = it.id,
            badge = it.badge,
            price = it.price.value,
            departureAirport = it.departure.airport,
            departureDate = it.departure.date,
            arrivalAirport = it.arrival.airport,
            arrivalDate = it.arrival.date,
            hasTransfer = it.hasTransfer
        )
    }
}