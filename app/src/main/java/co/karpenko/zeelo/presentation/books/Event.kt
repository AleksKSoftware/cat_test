package co.karpenko.zeelo.presentation.books

import co.karpenko.zeelo.data.mapper.Book

sealed class Event {
    object EmptyDetails : Event()
    object EmptyFields : Event()
    object ErrorAddingDetails : Event()
    data class BookDetailsEvent(val bookDetails: Book) : Event()
    data class AddedItem(val book: Book) : Event()
}
