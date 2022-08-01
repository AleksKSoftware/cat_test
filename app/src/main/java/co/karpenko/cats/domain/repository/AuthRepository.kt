package co.karpenko.cats.domain.repository

import co.karpenko.cats.data.store.AuthStore
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 30/7/22.
 * java.karpenko@gmail.com
 */
class AuthRepository @Inject constructor(private val authStore: AuthStore) {

    suspend fun signIn(
        email: String,
        password: String?,
    ) = authStore.authByEmail(email, password)
}