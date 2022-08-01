package co.karpenko.cats.presentation.login

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import co.karpenko.cats.R
import co.karpenko.cats.common.lifecycle.observe
import co.karpenko.cats.presentation.cats.CatsActivity
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Glide.with(this)
            .load("https://i.guim.co.uk/img/media/26392d05302e02f7bf4eb143bb84c8097d09144b/446_167_3683_2210/master/3683.jpg?width=1200&height=900&quality=85&auto=format&fit=crop&s=11e949fc5d06576bc8b80ec192896753")
            .into(loginImage)

        observe(viewModel.event) {
            when (it) {
                LoginViewModel.Event.Done -> showMainScreen()
                is LoginViewModel.Event.Error -> showError(it.ex)

            }
        }


        etPassword.doAfterTextChanged {
            viewModel.onPasswordChanged(it?.toString()?.trim())
        }


        etEmail.doAfterTextChanged {
            viewModel.onEmailChanged(it?.toString()?.trim())
        }

        observe(viewModel.isPasswordAndEmailCtaEnabled(), btnSignIn::setEnabled)

        btnSignIn.setOnClickListener {
            viewModel.onSignInClick()
        }

    }

    private fun showError(error: Exception) = Toast.makeText(this, "Error login ", Toast.LENGTH_SHORT).show()


    private fun showMainScreen() {
        CatsActivity.launch(this)
        finish()
    }


}