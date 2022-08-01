package co.karpenko.cats.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.karpenko.cats.data.db.cats.CatsDao
import co.karpenko.cats.data.db.remote_keys.RemoteKeys
import co.karpenko.cats.data.db.remote_keys.RemoteKeysDao
import co.karpenko.cats.data.mapper.Cat

/**
 * Created by Alexander Karpenko on 30/07/22.
 * java.karpenko@gmail.com
 */

@Database(
    entities = [Cat::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun catsDao(): CatsDao

    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "cats_db")
                .fallbackToDestructiveMigration()
                .build()
    }
}
