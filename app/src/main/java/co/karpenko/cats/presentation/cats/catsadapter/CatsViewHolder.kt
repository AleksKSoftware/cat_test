package co.karpenko.cats.presentation.cats.catsadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.karpenko.cats.R
import co.karpenko.cats.data.mapper.Cat
import co.tiim.testkarpenkoalex.domain.base.inflate
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_holder_cat.view.*

/**
 * Created by Alexander Karpenko on 30/07/22.
 * java.karpenko@gmail.com
 */

class CatsViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        parent.inflate(R.layout.view_holder_cat)
    ) {

    fun bind(cat: Cat?) = with(itemView) {
        tvName.text = cat?.name
        tvName.text = cat?.name
        Glide.with(context)
            .load(cat?.linkImage)
            .circleCrop()
            .into(ivCover)
    }
}
