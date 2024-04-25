package ru.tk4dmitriy.screens.airfares.ui.adapter.offers_tickets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.tk4dmitriy.core.utils.ui.delegateAdapter.AdapterDelegate
import ru.tk4dmitriy.core.utils.ui.delegateAdapter.DelegateItem
import ru.tk4dmitriy.screens.airfares.R
import ru.tk4dmitriy.screens.airfares.databinding.ItemOfferTicketBinding
import ru.tk4dmitriy.screens.airfares.ui.models.OfferTicketUi

internal class OffersTicketsDelegate : AdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder(
            ItemOfferTicketBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateItem) {
        (holder as ViewHolder).bind(item.content() as OfferTicketUi)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is OfferTicketDelegateItem

    class ViewHolder(private val binding: ItemOfferTicketBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OfferTicketUi) = with(binding) {
            tvTitle.text = item.title
            tvTimeRange.text = item.timeRange
            tvPrice.text = item.price.formatPrice()
            bindIcon()
        }

        private fun String.formatPrice(): String {
            val postfix = itemView.context.resources.getString(R.string.ruble)
            return "$this $postfix"
        }

        private fun bindIcon() {
            when {
                (absoluteAdapterPosition) % 3 == 0 -> binding.ivIcon.setImageDrawable(
                    ContextCompat.getDrawable(itemView.context, R.drawable.ic_circle_red))
                (absoluteAdapterPosition) % 3 == 1 -> binding.ivIcon.setImageDrawable(
                    ContextCompat.getDrawable(itemView.context, R.drawable.ic_circle_blue))
                else -> binding.ivIcon.setImageDrawable(
                    ContextCompat.getDrawable(itemView.context, R.drawable.ic_circle_white))
            }
        }
    }
}