package co.karpenko.zeelo.presentation.books_details.add_item

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.karpenko.zeelo.data.mapper.Book
import co.karpenko.zeelo.domain.usecases.adding_book.AddingBookInteractor
import co.karpenko.zeelo.presentation.books.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 25/4/22.
 * java.karpenko@gmail.com
 */

@HiltViewModel
class AddingBookDialogViewModel @Inject constructor(
    private val addingBookInteractor: AddingBookInteractor,
) : ViewModel() {

    val event = MutableLiveData<Event>()
    var title: String = ""
    var author: String = ""
    var price: String = ""

    fun addBookDetails() {
        if (title.isEmpty() && author.isEmpty() && price.isEmpty()) {
            event.value = Event.EmptyFields
        } else {
            viewModelScope.launch {
                val book = Book(1, title,"http://dummyimage.com/243x100.png/dddddd/000000", author,price)
                addingBookInteractor.addingBook(book).fold(
                    onSuccess = {
                        event.value = Event.AddedItem(book)
                    },
                    onFailure = { event.value = Event.ErrorAddingDetails }
                )
            }
        }
    }


    fun onFormTitleChanged(title: String) {
        this.title = title
    }

    fun onFormAuthorChanged(author: String) {
        this.author = author
    }

    fun onFormPriceChanged(price: String) {
        this.price = price
    }
}
