package ru.tk4dmitriy.screens.airfares.ui.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.tk4dmitriy.screens.airfares.R

internal class OfferTicketItemDecoration(
    context: Context
) : RecyclerView.ItemDecoration() {

    private val dividerHeight = context.resources.getDimension(R.dimen.offer_ticket_line_height)
    private val dividerPaint = Paint()

    init {
        dividerPaint.color = ContextCompat.getColor(context, ru.tk4dmitriy.core.res.R.color.grey_4)
        dividerPaint.style = Paint.Style.FILL
    }

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val adapter = parent.adapter ?: return

        outRect.left = view.resources.getDimension(R.dimen.offer_ticket_m_left).toInt()
        outRect.top = view.resources.getDimension(R.dimen.offer_ticket_m_top).toInt()
        outRect.right = view.resources.getDimension(R.dimen.offer_ticket_m_right).toInt()
        outRect.bottom = view.resources.getDimension(R.dimen.offer_ticket_m_bottom).toInt()

        if (position == adapter.itemCount - 1) {
            outRect.bottom = view.resources.getDimension(R.dimen.offer_ticket_last_m_bottom).toInt()
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft + parent.resources.getDimension(R.dimen.offer_ticket_line_m_left).toInt()
        val right = parent.width - parent.paddingRight - parent.resources.getDimension(R.dimen.offer_ticket_m_right).toInt()

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin + parent.resources.getDimension(R.dimen.offer_ticket_line_m_bottom).toInt()
            val bottom = top + dividerHeight

            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom, dividerPaint)
        }
    }
}