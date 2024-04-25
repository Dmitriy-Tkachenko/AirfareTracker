package ru.tk4dmitriy.data.tickets.impl

import android.util.Log
import ru.tk4dmitriy.data.tickets.api.Ticket
import ru.tk4dmitriy.data.tickets.api.TicketsRepository
import ru.tk4dmitriy.data.tickets.impl.localSource.TicketsLocalSource
import javax.inject.Inject

internal class TicketsRepositoryImpl @Inject constructor(
    private val ticketsLocalSource: TicketsLocalSource
) : TicketsRepository {
    override suspend fun getTickets(): List<Ticket> {
        val a =  ticketsLocalSource.getTickets()
        a.forEach {
            Log.d("TicketsRepositoryImpl22222", it.id.toString())
            Log.d("TicketsRepositoryImpl22222", it.badge.toString())
            Log.d("TicketsRepositoryImpl22222", it.price.toString())
            Log.d("TicketsRepositoryImpl22222", it.arrivalDate.toString())
            Log.d("TicketsRepositoryImpl22222", it.arrivalAirport.toString())
            Log.d("TicketsRepositoryImpl22222", it.departureDate.toString())
            Log.d("TicketsRepositoryImpl22222", it.departureAirport.toString())
            Log.d("TicketsRepositoryImpl22222", it.hasTransfer.toString())
        }
        return a
    }
}