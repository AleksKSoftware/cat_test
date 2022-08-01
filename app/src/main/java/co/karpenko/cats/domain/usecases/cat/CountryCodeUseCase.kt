package co.karpenko.cats.domain.usecases.cat

import co.karpenko.cats.domain.repository.CatsRepository
import co.tiim.testkarpenkoalex.domain.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 1/8/22.
 * java.karpenko@gmail.com
 */
class CountryCodeUseCase @Inject constructor(
    private val repository: CatsRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase(dispatcher) {

    suspend fun fetch() = wrapResult {
        repository.getCountryCodes()
    }
}