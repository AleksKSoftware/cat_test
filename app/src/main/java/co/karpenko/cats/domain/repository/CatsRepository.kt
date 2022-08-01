package co.karpenko.cats.domain.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import co.karpenko.cats.data.db.AppDatabase
import co.karpenko.cats.data.mapper.Cat
import co.karpenko.cats.data.store.CatsCloudStore
import co.karpenko.cats.data.store.PageRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 30/07/22.
 * java.karpenko@gmail.com
 */

class CatsRepository @Inject constructor(
    private val bookCloudStore: CatsCloudStore,
    private val appDatabase: AppDatabase,
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getCats(): Flow<PagingData<Cat>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),
            remoteMediator = PageRemoteMediator(appDatabase, bookCloudStore)
        ) {

            appDatabase.catsDao().postAllCats()
        }.flow
    }

    suspend fun searchCatsDetails(id: String): List<Cat> = requireNotNull(bookCloudStore.searchCatsDetails(id))

    fun getCatsDetails(id: String): Cat = requireNotNull(appDatabase.catsDao().getCat(id))

    fun getCountryCodes(): List<String> = appDatabase.catsDao().getCountryCodes()


    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }
}
