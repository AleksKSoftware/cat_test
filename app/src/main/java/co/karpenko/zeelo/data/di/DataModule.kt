package co.karpenko.zeelo.data.di

import android.content.Context
import co.karpenko.zeelo.data.db.AppDatabase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * Created by Alexander Karpenko on 02/05/22.
 * java.karpenko@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideUserProfilDao(db: AppDatabase) = db.booksDao()

    @Singleton
    @Provides
    fun provideRemoteKeysDao(db: AppDatabase) = db.remoteKeysDao()
}
