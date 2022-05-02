package co.karpenko.zeelo.presentation.widgets

import androidx.recyclerview.widget.DiffUtil

/**
 * Created by Alexander Karpenko on 02.05.22.
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
