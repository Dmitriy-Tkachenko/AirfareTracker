package ru.tk4dmitriy.airfaretracker.navigation

import ru.tk4dmitriy.airfaretracker.R
import ru.tk4dmitriy.screens.airfares.navigation.AirfaresNavigation

class AirfaresNavigationImpl : AirfaresNavigation {
    override fun toArrivalDialogFragment(): Int =
        R.id.action_airfaresFragment_to_arrivalDialogFragment
    override fun toDifficultRouteFragment(): Int =
        R.id.action_arrivalDialogFragment_to_difficultRoutFragment

    override fun toWeekendFragment(): Int =
        R.id.action_arrivalDialogFragment_to_weekendFragment

    override fun toHotTicketsFragment(): Int =
        R.id.action_arrivalDialogFragment_to_hotTicketsFragment

    override fun toOffersTicketsFragment(): Int =
        R.id.action_arrivalDialogFragment_to_offersTicketsFragment

    override fun toTicketsFragment(): Int =
        R.id.action_offersTicketsFragment_to_ticketsFragment
}