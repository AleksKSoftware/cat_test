package co.tiim.testkarpenkoalex.domain.base

import co.tiim.testkarpenkoalex.domain.Result.Failure
import co.tiim.testkarpenkoalex.domain.Result.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Created by Alexander Karpenko on 02.05.2022.
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
