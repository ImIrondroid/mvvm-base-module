package com.iron.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T>(
    itemCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseViewHolder<T>>(itemCallback) {
    //ListAdapter include methods of submitList

    var limitedItemcount: Int? = null

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.onBind(currentList.getOrNull(position))
    }

    override fun getItemCount(): Int =
        // * Ensures that this value is not greater than the specified [maximumValue].
        limitedItemcount?.coerceAtMost(currentList.size)
            ?: super.getItemCount()
}