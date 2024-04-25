package ru.tk4dmitriy.screens.airfares.ui.adapter.tickets

import ru.tk4dmitriy.core.utils.ui.delegateAdapter.DelegateItem
import ru.tk4dmitriy.screens.airfares.ui.models.TicketUi

internal class TicketDelegateItem(
    val id: Int,
    private val value: TicketUi
) : DelegateItem {
    override fun content(): Any = value
    override fun id(): Int = id
    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as TicketDelegateItem).value == content()
    }
}