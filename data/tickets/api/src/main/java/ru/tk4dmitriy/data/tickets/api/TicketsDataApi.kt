package ru.tk4dmitriy.data.tickets.api

interface TicketsDataApi {
    fun getTicketsRepository() : TicketsRepository
}