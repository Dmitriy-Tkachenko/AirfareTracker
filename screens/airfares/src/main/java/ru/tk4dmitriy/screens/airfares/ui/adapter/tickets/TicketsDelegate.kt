package ru.tk4dmitriy.screens.airfares.ui.adapter.tickets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.tk4dmitriy.core.utils.Utils
import ru.tk4dmitriy.core.utils.ui.delegateAdapter.AdapterDelegate
import ru.tk4dmitriy.core.utils.ui.delegateAdapter.DelegateItem
import ru.tk4dmitriy.screens.airfares.R
import ru.tk4dmitriy.screens.airfares.databinding.ItemOfferBinding
import ru.tk4dmitriy.screens.airfares.databinding.ItemTicketBinding
import ru.tk4dmitriy.screens.airfares.ui.models.OfferUi
import ru.tk4dmitriy.screens.airfares.ui.models.TicketUi

internal class TicketsDelegate : AdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder(
            ItemTicketBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateItem) {
        (holder as ViewHolder).bind(item.content() as TicketUi)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is TicketDelegateItem

    class ViewHolder(private val binding: ItemTicketBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TicketUi) = with(binding) {
            tvBadge.text = item.badge
            tvPrice.text = item.price.formatPrice()
            tvDepartureTime.text = item.departureTime
            tvDepartureAirport.text = item.departureAirport
            tvArrivalTime.text = item.arrivalTime
            tvArrivalAirport.text = item.arrivalAirport
            tvTravelTime.text = item.travelTime.formatTime()

            if (item.badge == null) tvBadge.visibility = View.GONE
            if (item.hasTransfer) {
                tvSlash.visibility = View.GONE
                tvNoTransfers.visibility = View.GONE
            } else {
                tvSlash.visibility = View.VISIBLE
                tvNoTransfers.visibility = View.VISIBLE
            }
        }

         private fun String.formatPrice(): String {
            val postfix = itemView.context.resources.getString(R.string.ruble)
            return "$this $postfix"
        }

        private fun String.formatTime(): String {
            val postfix = itemView.context.resources.getString(R.string.hours_on_the_road)
            return "$this$postfix"
        }
    }
}