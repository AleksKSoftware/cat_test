package co.karpenko.zeelo.presentation.books.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.karpenko.zeelo.R
import co.karpenko.zeelo.data.mapper.Book
import co.tiim.testkarpenkoalex.domain.base.inflate
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_holder_book.view.*

/**
 * Created by Alexander Karpenko on 02.05.2022.
 * java.karpenko@gmail.com
 */

class BooksViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        parent.inflate(R.layout.view_holder_book)
    ) {

    fun bind(book: Book?) = with(itemView) {
        tvName.text = book?.author
        Glide.with(context)
            .load(book?.image)
            .circleCrop()
            .into(ivCover)

    }
}
