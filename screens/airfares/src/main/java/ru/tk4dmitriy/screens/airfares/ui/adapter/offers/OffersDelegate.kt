package ru.tk4dmitriy.screens.airfares.ui.adapter.offers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.tk4dmitriy.core.utils.ui.delegateAdapter.AdapterDelegate
import ru.tk4dmitriy.core.utils.ui.delegateAdapter.DelegateItem
import ru.tk4dmitriy.screens.airfares.R
import ru.tk4dmitriy.screens.airfares.databinding.ItemOfferBinding
import ru.tk4dmitriy.screens.airfares.ui.models.OfferUi

internal class OffersDelegate : AdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder(
            ItemOfferBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateItem) {
        (holder as ViewHolder).bind(item.content() as OfferUi)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is OfferDelegateItem

    class ViewHolder(private val binding: ItemOfferBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OfferUi) = with(binding) {
            tvTitle.text = item.title
            tvTown.text = item.town
            tvPrice.text = item.price.formatPrice()
            Glide.with(itemView.context).asBitmap().load(item.imgPreview).into(imgPreview)
        }

        private fun String.formatPrice(): String {
            val prefix = itemView.context.resources.getString(R.string.price_prefix)
            val postfix = itemView.context.resources.getString(R.string.ruble)
            return " $prefix $this $postfix"
        }
    }
}