package ru.tk4dmitriy.screens.airfares.ui.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.tk4dmitriy.screens.airfares.R

internal class TicketItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.top = view.resources.getDimension(R.dimen.ticket_m_top).toInt()
    }
}