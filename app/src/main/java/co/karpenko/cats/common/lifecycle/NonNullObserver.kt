package co.karpenko.cats.common.lifecycle

import androidx.lifecycle.Observer

class NonNullObserver<T : Any>(val block: (T) -> Unit) : Observer<T> {
    override fun onChanged(data: T?) {
        requireNotNull(data)
        block(data)
    }
}
