package co.karpenko.cats.presentation.cats.catsadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import co.karpenko.cats.presentation.widgets.simpleItemCallback

/**
 * Created by Alexander Karpenko on 1/8/22.
 * java.karpenko@gmail.com
 */
class CatsFilterAdapter(
    val onClick: (id: String?) -> Unit,
) : ListAdapter<String, CatsFilterViewHolder>(simpleItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsFilterViewHolder {
        return CatsFilterViewHolder(parent).apply {
            itemView.setOnClickListener { onClick(getItem(absoluteAdapterPosition)) }
        }
    }

    override fun onBindViewHolder(holderItem: CatsFilterViewHolder, position: Int) {
        holderItem.bind(getItem(position))
    }
}
