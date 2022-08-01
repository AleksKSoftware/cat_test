package co.karpenko.cats.data

import co.karpenko.cats.data.entity.AuthResult
import co.karpenko.cats.domain.Result
import co.karpenko.cats.domain.repository.AuthRepository
import co.karpenko.cats.domain.usecases.sign_in.SignInUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


/**
 * Created by Alexander Karpenko on 1/8/22.
 * java.karpenko@gmail.com
 */
class SignInUseCaseTest {

    @Mock
    private lateinit var authRepository: AuthRepository


    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `authentication and getting token`() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val signInUseCase = SignInUseCase(dispatcher, authRepository)

        `when`(authRepository.signIn(anyString(), anyString()))
            .thenReturn(AuthResult("token"))

        val token =
            signInUseCase.launchEmailSignIn("test",
                "test")
        verify(authRepository, times(1)).signIn(anyString(), anyString());
        assertEquals(Result.Success(AuthResult("token")), token)
    }
}