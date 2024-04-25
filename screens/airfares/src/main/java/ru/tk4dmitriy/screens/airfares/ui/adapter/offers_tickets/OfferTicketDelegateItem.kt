package ru.tk4dmitriy.screens.airfares.ui.adapter.offers_tickets

import ru.tk4dmitriy.core.utils.ui.delegateAdapter.DelegateItem
import ru.tk4dmitriy.screens.airfares.ui.models.OfferTicketUi

internal class OfferTicketDelegateItem (
    val id: Int,
    private val value: OfferTicketUi
) : DelegateItem {
    override fun content(): Any = value
    override fun id(): Int = id
    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as OfferTicketDelegateItem).value == content()
    }
}