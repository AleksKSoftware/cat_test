package co.karpenko.cats.data.store

import co.karpenko.cats.data.entity.AuthResult
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 30/7/22.
 * java.karpenko@gmail.com
 */
class AuthStore @Inject constructor() {

    fun authByEmail(
        email: String,
        password: String?,
    ): AuthResult {
        return AuthResult("token")

    }
}