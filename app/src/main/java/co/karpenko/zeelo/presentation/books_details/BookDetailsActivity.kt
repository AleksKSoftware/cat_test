package co.karpenko.zeelo.presentation.books_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import co.karpenko.zeelo.R
import co.karpenko.zeelo.common.lifecycle.observe
import co.karpenko.zeelo.data.mapper.Book
import co.karpenko.zeelo.presentation.books.Event
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_book_details.*

/**
 * Created by Alexander Karpenko on 02.05.2022.
 * java.karpenko@gmail.com
 */

@AndroidEntryPoint
class BookDetailsActivity : AppCompatActivity() {

    private val viewModel: BookDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        val bookId = intent.getIntExtra(bookId, 0)
        viewModel.getBookDetails(bookId)

        observe(viewModel.event) {
            when (it) {
                is Event.EmptyDetails -> {
                    Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show()
                }
                is Event.BookDetailsEvent -> {
                    showBookDetails(it.bookDetails)
                }
            }
        }
        toolbar.setNavigationOnClickListener { finish() }
    }

    private fun showBookDetails(book: Book) {
        tvName.text = book.author
        tvAuthor.text = book.title
        tvPrice.text = book.price
        Glide.with(this)
            .load(book.image)
            .circleCrop()
            .into(imgBook)
    }

    companion object {
        private const val bookId = "bookId"

        fun launch(idBook: Int, context: Context) {
            return context.startActivity(
                Intent(context, BookDetailsActivity::class.java).also {
                    it.putExtra(bookId, idBook)
                }
            )
        }
    }
}
