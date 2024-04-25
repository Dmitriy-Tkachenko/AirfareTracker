package ru.tk4dmitriy.screens.airfares.navigation

interface AirfaresNavigation {
    fun toArrivalDialogFragment(): Int
    fun toDifficultRouteFragment(): Int
    fun toWeekendFragment(): Int
    fun toHotTicketsFragment(): Int
    fun toOffersTicketsFragment(): Int
    fun toTicketsFragment(): Int
}