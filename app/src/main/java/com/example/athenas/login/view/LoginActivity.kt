package com.example.athenas.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.athenas.MainActivity
import com.example.athenas.common.base.DependencyInjector
import com.example.athenas.common.util.TextWatcher
import com.example.athenas.databinding.ActivityLoginBinding
import com.example.athenas.login.Login
import com.example.athenas.login.presentation.LoginPresenter

class LoginActivity : AppCompatActivity(), Login.View{

    private lateinit var binding: ActivityLoginBinding
    override lateinit var presenter: Login.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = LoginPresenter(this, DependencyInjector.loginRepository())


        with(binding) {
            loginEditUser.addTextChangedListener(watcher)
            loginEditPassword.addTextChangedListener(watcher)
            loginBtnEnter.setOnClickListener {
                presenter.login(loginEditUser.text.toString(), loginEditPassword.text.toString())

            }
        }

    }



    private val watcher = TextWatcher {
        binding.loginBtnEnter.isEnabled = binding.loginEditUser.text.toString().isNotEmpty()
                && binding.loginEditPassword.text.toString().isNotEmpty()
        binding.loginEditUserInput.error = null
        binding.loginEditPasswordInput.error = null
    }
    private fun goToRegisterScreen() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding.loginEditUserInput.error = emailError?.let { getString(it) }
    }
    override fun displayPasswordFailure(passwordError: Int?) {
        passwordError?.let {
            binding.loginEditPasswordInput.error = getString(it)
        }
    }

    override fun showProgress(enabled: Boolean) {
        binding.loginBtnEnter.isEnabled = enabled
    }

    override fun onUserAuthenticated() {
        goToRegisterScreen()
    }

    override fun onUserUnAuthorized(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}