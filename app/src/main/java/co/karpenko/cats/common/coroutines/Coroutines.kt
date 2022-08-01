package co.karpenko.cats.common.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

/**
 * Created by Alexander Karpenko on 30/07/22.
 * java.karpenko@gmail.com
 */

@Suppress("unused")
val Dispatchers.Single
    get() = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
