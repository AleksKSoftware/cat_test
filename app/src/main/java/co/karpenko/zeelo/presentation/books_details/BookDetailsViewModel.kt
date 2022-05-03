package co.karpenko.zeelo.presentation.books_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.karpenko.zeelo.domain.usecases.book_details.BookDetailsInteractor
import co.karpenko.zeelo.presentation.books.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 02.05.2022.
 * java.karpenko@gmail.com
 */

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val bookDetailsInteractor: BookDetailsInteractor,
) : ViewModel() {

    val event = MutableLiveData<Event>()

    fun getBookDetails(id: Int) {
        viewModelScope.launch {
            bookDetailsInteractor.fetchBookDetails(id).fold(
                onSuccess = {
                    event.value = Event.BookDetailsEvent(it)
                },
                onFailure = { event.value = Event.EmptyDetails }
            )
        }
    }
}
