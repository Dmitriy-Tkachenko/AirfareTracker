package ru.tk4dmitriy.screens.airfares.ui.adapter

import ru.tk4dmitriy.core.utils.ui.delegateAdapter.DelegateItem
import ru.tk4dmitriy.screens.airfares.ui.models.OfferUi

internal class OfferDelegateItem(
    val id: Int,
    private val value: OfferUi
) : DelegateItem {
    override fun content(): Any = value
    override fun id(): Int = id
    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as OfferDelegateItem).value == content()
    }
}