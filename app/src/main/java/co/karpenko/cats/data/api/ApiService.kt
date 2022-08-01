package co.karpenko.cats.data.api

import co.karpenko.cats.data.entity.CatModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

/**
 * Created by Alexander Karpenko on 30/07/22.
 * java.karpenko@gmail.com
 */

@Singleton
interface ApiService {

    @GET("v1/breeds")
    suspend fun getCats(
        @Query("page") page: Int = 0,
        @Query("limit") limit: Int = 0,
        @Query("attach_breed") attachBreed: Int = 0,
    ): List<CatModel>

    @GET("v1/breeds/search")
    suspend fun searchCat(@Query("q") search: String): List<CatModel>
}
