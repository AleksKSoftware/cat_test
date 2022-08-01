package co.karpenko.cats.presentation.cats.catsadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.karpenko.cats.R
import co.tiim.testkarpenkoalex.domain.base.inflate
import kotlinx.android.synthetic.main.layout_cat_filter.view.*

/**
 * Created by Alexander Karpenko on 1/8/22.
 * java.karpenko@gmail.com
 */
class CatsFilterViewHolder(
    parent: ViewGroup,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.layout_cat_filter)) {

    fun bind(item: String) {
        itemView.txtFilter.text = item
    }
}
