package co.karpenko.cats.domain.usecases.cat

import co.karpenko.cats.domain.repository.CatsRepository
import co.tiim.testkarpenkoalex.domain.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 30/07/22.
 * java.karpenko@gmail.com
 */
class CatsUseCase @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val repository: CatsRepository,
) : BaseUseCase(dispatcher) {

    fun fetchCats() = repository.getCats()
}
