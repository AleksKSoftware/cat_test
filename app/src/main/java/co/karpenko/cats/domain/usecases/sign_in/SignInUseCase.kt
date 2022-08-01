package co.karpenko.cats.domain.usecases.sign_in

import co.karpenko.cats.domain.repository.AuthRepository
import co.tiim.testkarpenkoalex.domain.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 30/7/22.
 * java.karpenko@gmail.com
 */
class SignInUseCase @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val authRepository: AuthRepository,
) : BaseUseCase(dispatcher) {


    suspend fun launchEmailSignIn(email: String, password: String?) = wrapResult {
        authRepository.signIn(email, password)
    }
}
