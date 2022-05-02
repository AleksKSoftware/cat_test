package co.karpenko.zeelo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import co.karpenko.zeelo.data.Converters
import co.karpenko.zeelo.data.db.remote_keys.RemoteKeys
import co.karpenko.zeelo.data.db.remote_keys.RemoteKeysDao
import co.karpenko.zeelo.data.mapper.Book
import co.karpenko.zeelo.data.db.books.BookDao

/**
 * Created by Alexander Karpenko on 2/5/22.
 * java.karpenko@gmail.com
 */

@Database(
    entities = [Book::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun booksDao(): BookDao

    abstract fun remoteKeysDao(): RemoteKeysDao


    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "book_db")
                .fallbackToDestructiveMigration()
                .build()
    }

}