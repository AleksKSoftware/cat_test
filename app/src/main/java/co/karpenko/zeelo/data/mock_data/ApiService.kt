package co.karpenko.zeelo.data.mock_data

import android.content.Context
import co.karpenko.zeelo.data.entity.BookModel
import co.karpenko.zeelo.data.readFile
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Alexander Karpenko on 02/05/22.
 * java.karpenko@gmail.com
 */

@Singleton
class ApiService @Inject constructor(
    @ApplicationContext private val context: Context,
    gson: Gson,
) {
    private val content: String = context.assets.readFile("book_data.json")
    private var listBooks = lazy  { gson.fromJson(content, Array<BookModel>::class.java).toMutableList() }.value

    fun getBooksModel(): List<BookModel> = listBooks

    fun getBookDetailsModel(id: Int): BookModel? = listBooks.firstOrNull { it.id == id }


    // It will send to server side and return id of the object.
    fun addBook(book: BookModel): List<BookModel> {
        listBooks.add(0, book)
        return listBooks
    }
}