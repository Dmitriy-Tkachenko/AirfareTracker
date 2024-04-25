package ru.tk4dmitriy.data.tickets.impl.localSource

import ru.tk4dmitriy.data.tickets.api.Ticket


internal interface TicketsLocalSource {
    suspend fun getTickets() : List<Ticket>
}