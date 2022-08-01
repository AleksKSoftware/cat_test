package co.karpenko.cats.domain

/**
 * Created by Alexander Karpenko on 30/07/22.
 * java.karpenko@gmail.com
 */
sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Failure(val error: Error) : Result<Nothing>()

    fun fold(onSuccess: (T) -> Unit, onFailure: (Error) -> Unit) {
        when (this) {
            is Success -> onSuccess(data)
            is Failure -> onFailure(error)
        }
    }

    fun onSuccess(onSuccess: (T) -> Unit): Result<T> {
        if (this is Success) onSuccess(data)
        return this
    }

    fun onFailure(onFailure: (Error) -> Unit) {
        if (this is Failure) onFailure(error)
    }

    val isSuccess get() = this is Success
    val isFailure get() = this is Failure
}
