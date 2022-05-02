package co.karpenko.zeelo.domain.usecases.adding_book

import co.karpenko.zeelo.data.mapper.Book
import co.karpenko.zeelo.domain.repository.BookRepository
import co.tiim.testkarpenkoalex.domain.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 02.05.2022.
 * java.karpenko@gmail.com
 */

class AddingBookInteractor @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val repository: BookRepository,
) : BaseUseCase(dispatcher) {

    suspend fun addingBook(book: Book) = wrapResult {
        repository.addingBook(book)
    }
}