package co.karpenko.zeelo.common.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import java.io.Closeable
import kotlin.coroutines.CoroutineContext

/**
 * Created by Alexander Karpenko on 02.05.2022.
 * java.karpenko@gmail.com
 */

class ClosableMainScope : Closeable, CoroutineScope {
    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main.immediate

    // todo: close is executed from view model if reference is added as a tag
    // see viewModelScope implementation
    // solution create a kotlin delegate to set a tag
    override fun close() {
        coroutineContext.cancel()
    }

    fun cancelChildren() {
        coroutineContext.cancelChildren()
    }
}
