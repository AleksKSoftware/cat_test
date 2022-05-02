package co.karpenko.zeelo.data.db.books

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.karpenko.zeelo.data.mapper.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM books")
    fun postAllBooks(): PagingSource<Int, Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(listIds: List<Book>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Query("DELETE FROM books")
    suspend fun clearBooks()
}
