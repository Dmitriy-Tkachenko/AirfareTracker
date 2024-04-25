package ru.tk4dmitriy.features.tickets.impl

import ru.tk4dmitriy.data.tickets.api.TicketsRepository
import ru.tk4dmitriy.features.tickets.api.GetTicketsFeatureCase
import ru.tk4dmitriy.features.tickets.api.Ticket
import javax.inject.Inject

class GetTicketsFeatureCaseImpl @Inject constructor(
    private var ticketsRepository: TicketsRepository
): GetTicketsFeatureCase {
    override suspend fun invoke(): List<Ticket> =
        ticketsRepository.getTickets().map {
            Ticket(
                id = it.id,
                badge = it.badge,
                price = it.price,
                departureDate = it.departureDate,
                departureAirport = it.departureAirport,
                arrivalDate = it.arrivalDate,
                arrivalAirport = it.arrivalAirport,
                hasTransfer = it.hasTransfer
            )
        }
}