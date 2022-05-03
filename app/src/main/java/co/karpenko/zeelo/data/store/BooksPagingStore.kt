package co.karpenko.zeelo.data.store

import androidx.paging.PagingSource
import androidx.paging.PagingState
import co.karpenko.zeelo.data.mapper.Book
import co.karpenko.zeelo.domain.repository.BookRepository.Companion.NETWORK_PAGE_SIZE
import com.bumptech.glide.load.HttpException
import java.io.IOException

/**
 * A [PagingSource] that loads articles for paging. The [Int] is the paging key or query that is used to fetch more
 * data, and the [Book] specifies that the [PagingSource] fetches an [Book] [List].
 */
// The class could be replaced like a source of data for pagination list.
class BookPagingSource(
    private val bookCloudStore: BooksCloudStore,
) : PagingSource<Int, Book>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        val position = params.key ?: STARTING_PAGE_INDEX
        val lastPosition = NETWORK_PAGE_SIZE + position
        return try {
            val listBooks = bookCloudStore.getBooks(position, lastPosition)
            val nextKey = if (listBooks.isEmpty()) {
                null
            } else {
                position + NETWORK_PAGE_SIZE
            }
            LoadResult.Page(
                data = listBooks,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        const val STARTING_PAGE_INDEX = 0
    }
}
