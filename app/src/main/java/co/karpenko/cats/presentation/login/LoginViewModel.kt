package co.karpenko.cats.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.karpenko.cats.common.lifecycle.combineWith
import co.karpenko.cats.domain.isEmailValid
import co.karpenko.cats.domain.isUserPasswordValid
import co.karpenko.cats.domain.usecases.sign_in.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 30/7/22.
 * java.karpenko@gmail.com
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : ViewModel() {

    sealed class Event {
        object Done : Event()
        data class Error(val ex: Exception) : Event()
    }

    val event = MutableLiveData<Event>()
    private val isEmailCtaEnabled = MutableLiveData(false)
    private val isPasswordCtaEnabled = MutableLiveData(false)

    private lateinit var email: String
    private lateinit var password: String

    fun isPasswordAndEmailCtaEnabled() =
        isEmailCtaEnabled.combineWith(isPasswordCtaEnabled) { email, password ->
            email && password
        }

    fun onEmailChanged(email: String?) {
        this.email = email.orEmpty()
        isEmailCtaEnabled.value = email?.isEmailValid == true
    }

    fun onPasswordChanged(password: String?) {
        this.password = password.orEmpty()
        isPasswordCtaEnabled.value = password?.isUserPasswordValid == true
    }

    fun onSignInClick() = viewModelScope.launch {
        signInUseCase.launchEmailSignIn(email, password).fold(
            onSuccess = {
                event.value = Event.Done
            },
            onFailure = {
                event.value = Event.Error(java.lang.Exception("Exception Login"))
            }
        )
    }
}