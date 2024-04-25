package ru.tk4dmitriy.data.tickets.api

interface TicketsRepository {
    suspend fun getTickets(): List<Ticket>
}