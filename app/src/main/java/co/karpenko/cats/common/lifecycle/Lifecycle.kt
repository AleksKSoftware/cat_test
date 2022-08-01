@file:Suppress("unused")

package co.karpenko.cats.common.lifecycle

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

/**
 * Created by Alexander Karpenko on 30/07/22.
 * java.karpenko@gmail.com
 */

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) =
    liveData.observe(this, NonNullObserver(body))

fun <T : Any, L : LiveData<T>> Fragment.observe(liveData: L, body: (T) -> Unit) =
    liveData.observe(viewLifecycleOwner, NonNullObserver(body))

fun <T, L, M> LiveData<T>.combineWith(
    liveData: LiveData<L>,
    block: (T, L) -> M
): LiveData<M> {
    val result = MediatorLiveData<M>()
    result.addSource(this) {
        result.value = block(this.value!!, liveData.value!!)
    }
    result.addSource(liveData) {
        result.value = block(this.value!!, liveData.value!!)
    }
    return result
}