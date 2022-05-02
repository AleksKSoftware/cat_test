package co.karpenko.zeelo.common.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

/**
 * Created by Alexander Karpenko on 02.05.2022.
 * java.karpenko@gmail.com
 */


@Suppress("unused")
val Dispatchers.Single
    get() = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
