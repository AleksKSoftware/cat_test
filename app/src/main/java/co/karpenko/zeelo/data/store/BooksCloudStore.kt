package co.karpenko.zeelo.data.store

import co.karpenko.zeelo.data.mapper.Book
import co.karpenko.zeelo.data.mapper.BookMapper.toBook
import co.karpenko.zeelo.data.mapper.BookMapper.toBookModel
import co.karpenko.zeelo.data.mock_data.ApiService
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 02.05.2022.
 * java.karpenko@gmail.com
 */

class BooksCloudStore @Inject constructor(
    private val api: ApiService,
) {
    suspend fun getBooks(offset: Int, count: Int): List<Book> {
        delay(1000)
        var listBooks = api.getBooksModel()
        return if (listBooks.size >= count) {
            listBooks = listBooks.subList(offset, count)
            listBooks.map { toBook(it) }
        } else {
            emptyList()
        }
    }

    fun getBooksDetails(id: Int): Book? {
        val book = api.getBookDetailsModel(id)
        return book?.let { toBook(it) }
    }

    fun addBooks(book: Book) = api.addBook(toBookModel(book))
}
