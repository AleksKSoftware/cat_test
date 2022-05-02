package co.karpenko.zeelo.presentation.books

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import co.karpenko.zeelo.R
import co.karpenko.zeelo.presentation.books.adapter.BooksListAdapter
import co.karpenko.zeelo.presentation.books_details.BookDetailsActivity.Companion.launch
import co.karpenko.zeelo.presentation.books_details.add_item.AddingBookDialog
import co.karpenko.zeelo.presentation.books_details.add_item.AddingBookDialog.Companion.show
import co.karpenko.zeelo.presentation.widgets.ProgressStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by Alexander Karpenko on 02.05.22.
 * java.karpenko@gmail.com
 */

@AndroidEntryPoint
class BooksActivity : AppCompatActivity() {

    private val viewModel: BooksViewModel by viewModels()

    private val booksAdapter = BooksListAdapter(::onClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        listBooks.layoutManager = LinearLayoutManager(this)
        listBooks.setHasFixedSize(true)
        listBooks.adapter = booksAdapter

        listBooks.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = booksAdapter.withLoadStateFooter(
                footer = ProgressStateAdapter()
            )
        }

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.booksData.collectLatest {
                booksAdapter.submitData(it)
            }
        }
        fabAdd.setOnClickListener {
            showAddDialog()
        }
    }

    private fun showAddDialog() {
        show(supportFragmentManager)
    }

    private fun onClick(bookId: Int?) {
        bookId?.let { launch(it, this) }
    }
}
