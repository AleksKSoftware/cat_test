package co.karpenko.cats.presentation.cats.catsadapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import co.karpenko.cats.data.mapper.Cat
import co.karpenko.cats.presentation.widgets.simpleItemCallback

/**
 * Created by Alexander Karpenko on 30/07/22.
 * java.karpenko@gmail.com
 */

class CatsListAdapter(
    val clickListener: (id: String?) -> Unit,
) : PagingDataAdapter<Cat, CatsViewHolder>(
    simpleItemCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        return CatsViewHolder(parent).apply {
            itemView.setOnClickListener { clickListener(getItem(absoluteAdapterPosition)?.id) }
        }
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
