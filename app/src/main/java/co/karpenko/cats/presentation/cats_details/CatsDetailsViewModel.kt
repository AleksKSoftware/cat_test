package co.karpenko.cats.presentation.cats_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.karpenko.cats.domain.usecases.cat_details.CatsDetailsUseCase
import co.karpenko.cats.presentation.cats.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 30/07/22.
 * java.karpenko@gmail.com
 */

@HiltViewModel
class CatsDetailsViewModel @Inject constructor(
    private val bookDetailsInteractor: CatsDetailsUseCase,
) : ViewModel() {

    val event = MutableLiveData<Event>()

    fun getCatsDetails(id: String) {
        viewModelScope.launch {
            bookDetailsInteractor.fetchCatDetails(id).fold(
                onSuccess = {
                    event.value = Event.CatDetailsEvent(it)
                },
                onFailure = { event.value = Event.EmptyDetails }
            )
        }
    }
}
