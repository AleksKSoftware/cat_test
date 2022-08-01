package co.karpenko.cats.presentation.cats

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.karpenko.cats.domain.usecases.cat.CountryCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 1/8/22.
 * java.karpenko@gmail.com
 */

@HiltViewModel
class CatsFilterViewModel @Inject constructor(
    catsFilterUseCase: CountryCodeUseCase,
) : ViewModel() {

    val event = MutableLiveData<Event>()

    sealed class Event {
        data class Done(val itemFilter: List<String>) : Event()
        data class Error(val ex: Exception) : Event()
    }

    init {
        viewModelScope.launch {
            catsFilterUseCase.fetch().fold(
                onSuccess = {
                    val list = mutableListOf<String>()
                    list.add(NONE)
                    list.addAll(it)
                    event.value = Event.Done(list)
                },
                onFailure = {
                    event.value = Event.Error(java.lang.Exception("Exception couldn't get country codes "))
                }
            )
        }
    }

    companion object{
        const val NONE ="None"
    }
}