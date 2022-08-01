package co.karpenko.cats.presentation.cats

import co.karpenko.cats.data.mapper.Cat

sealed class Event {
    object EmptyDetails : Event()
    object EmptyFields : Event()
    object ErrorAddingDetails : Event()
    data class CatDetailsEvent(val catDetails: Cat) : Event()
    data class AddedItem(val cat: Cat) : Event()
}
