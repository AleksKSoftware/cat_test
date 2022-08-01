package co.karpenko.cats.data.store

import co.karpenko.cats.data.api.ApiService
import co.karpenko.cats.data.mapper.Cat
import co.karpenko.cats.data.mapper.CatMapper.toCat
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 30/07/22.
 * java.karpenko@gmail.com
 */

class CatsCloudStore @Inject constructor(
    private val api: ApiService,
) {
    suspend fun getCats(offset: Int, count: Int, breed: Int = 0): List<Cat> {
        return api.getCats(page = offset, limit = count, attachBreed = breed).map { toCat(it) }
    }

    suspend fun searchCatsDetails(search: String): List<Cat> = api.searchCat(search).map { toCat(it) }
}
