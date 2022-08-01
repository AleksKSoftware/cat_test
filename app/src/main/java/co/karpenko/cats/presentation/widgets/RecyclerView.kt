package co.karpenko.cats.presentation.widgets

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Alexander Karpenko on 30.07.22.
 * java.karpenko@gmail.com
 */

inline fun <reified T> simpleItemCallback() = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}
