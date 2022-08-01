package co.tiim.testkarpenkoalex.domain.base

import co.karpenko.cats.domain.Result.Failure
import co.karpenko.cats.domain.Result.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Created by Alexander Karpenko on 30/07/22.
 * java.karpenko@gmail.com
 */

open class BaseUseCase(
    private val dispatcher: CoroutineDispatcher,
) {
    protected suspend fun <T : Any> wrapResult(block: suspend () -> T) = withContext(dispatcher) {
        try {
            Success(block.invoke())
        } catch (expected: Exception) {
            expected.printStackTrace()
            Failure(Error(expected))
        }
    }
}
