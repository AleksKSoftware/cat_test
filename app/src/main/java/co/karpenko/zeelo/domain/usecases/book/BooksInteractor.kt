package co.karpenko.zeelo.domain.usecases.book

import co.karpenko.zeelo.domain.repository.BookRepository
import co.tiim.testkarpenkoalex.domain.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 02.05.2022.
 * java.karpenko@gmail.com
 */
class BooksInteractor @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val repository: BookRepository,
) : BaseUseCase(dispatcher) {

    fun fetchBooks() = repository.getBooks()
}
