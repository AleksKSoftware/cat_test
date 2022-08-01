package co.karpenko.cats.presentation.cats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import co.karpenko.cats.data.mapper.Cat
import co.karpenko.cats.domain.usecases.cat.CatsUseCase
import co.karpenko.cats.presentation.cats.CatsFilterViewModel.Companion.NONE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 30/07/22.
 * java.karpenko@gmail.com
 */

@HiltViewModel
class CatsViewModel @Inject constructor(
    rocketsUseCase: CatsUseCase,
) : ViewModel() {

    private val queryFlow = MutableStateFlow("")

    val catsData: Flow<PagingData<Cat>> = rocketsUseCase.fetchCats()
        .cachedIn(viewModelScope)
        .combine(queryFlow) { pagingData, query ->
            pagingData.filter {
                if (query.isEmpty() or (query == NONE)) {
                    true
                } else {
                    it.countryCode == query
                }
            }
        }

    fun setFilter(id: String) {
        queryFlow.value = id
    }
}
