package ru.tk4dmitriy.screens.airfares.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.tk4dmitriy.core.utils.ui.delegateAdapter.AdapterDelegate
import ru.tk4dmitriy.core.utils.ui.delegateAdapter.DelegateItem

internal class GlobalAdapter : ListAdapter<DelegateItem, RecyclerView.ViewHolder>(DelegateAdapterItemCallback()) {
    private val delegates: MutableList<AdapterDelegate> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        delegates[viewType].onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegates[getItemViewType(position)].onBindViewHolder(holder, getItem(position))
    }

    fun addDelegate(delegate: AdapterDelegate) {
        delegates.add(delegate)
    }

    override fun getItemViewType(position: Int): Int {
        return delegates.indexOfFirst { it.isOfViewType(currentList[position]) }
    }

    class DelegateAdapterItemCallback : DiffUtil.ItemCallback<DelegateItem>() {
        override fun areItemsTheSame(oldItem: DelegateItem, newItem: DelegateItem): Boolean {
            return oldItem::class == newItem::class && oldItem.id() == newItem.id()
        }

        override fun areContentsTheSame(oldItem: DelegateItem, newItem: DelegateItem): Boolean {
            return oldItem.compareToOther(newItem)
        }
    }
}