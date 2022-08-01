package co.karpenko.cats.data.di

import android.content.Context
import androidx.multidex.BuildConfig
import co.karpenko.cats.data.db.AppDatabase
import co.karpenko.cats.data.api.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Alexander Karpenko on 30/07/22.
 * java.karpenko@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private const val API_URL = "https://api.thecatapi.com/"

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCatsDao(db: AppDatabase) = db.catsDao()

    @Singleton
    @Provides
    fun provideRemoteKeysDao(db: AppDatabase) = db.remoteKeysDao()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return Retrofit.Builder()
            .client(clientBuilder.build())
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


}
