package ru.tk4dmitriy.screens.airfares.ui.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.tk4dmitriy.screens.airfares.R

internal class OfferItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val adapter = parent.adapter ?: return

        if (position != adapter.itemCount - 1) {
            outRect.right = view.resources.getDimension(R.dimen.offer_m_left).toInt()
        }
    }
}