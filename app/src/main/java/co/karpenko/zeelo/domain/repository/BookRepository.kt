package co.karpenko.zeelo.domain.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import co.karpenko.zeelo.data.db.AppDatabase
import co.karpenko.zeelo.data.mapper.Book
import co.karpenko.zeelo.data.store.BooksCloudStore
import co.karpenko.zeelo.data.store.PageRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 02.05.2022.
 * java.karpenko@gmail.com
 */

class BookRepository @Inject constructor(
    private val bookCloudStore: BooksCloudStore,
    private val appDatabase: AppDatabase,
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getBooks(): Flow<PagingData<Book>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),
            remoteMediator = PageRemoteMediator(appDatabase, bookCloudStore)
        ) {
            appDatabase.booksDao().postAllBooks()
        }.flow
    }

    fun getBookDetails(id: Int): Book =
        requireNotNull(bookCloudStore.getBooksDetails(id))

    suspend fun addingBook(book: Book) {
        requireNotNull(bookCloudStore.addBooks(book))
        appDatabase.booksDao().insert(book)
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }
}
