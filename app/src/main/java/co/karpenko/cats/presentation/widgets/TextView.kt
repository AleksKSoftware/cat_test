@file:Suppress("unused")

package co.karpenko.cats.presentation.widgets

import android.text.Editable
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Created by Alexander Karpenko on 30.07.22.
 * java.karpenko@gmail.com
 */

@ExperimentalCoroutinesApi
val EditText.afterTextChangedFlow: Flow<Editable?>
    get() = callbackFlow {
        val watcher = doAfterTextChanged { trySend(it).isSuccess }
        awaitClose { removeTextChangedListener(watcher) }
    }
