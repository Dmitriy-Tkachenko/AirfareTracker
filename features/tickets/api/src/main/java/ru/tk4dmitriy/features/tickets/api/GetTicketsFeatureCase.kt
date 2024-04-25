package ru.tk4dmitriy.features.tickets.api

interface GetTicketsFeatureCase {
    suspend operator fun invoke(): List<Ticket>
}