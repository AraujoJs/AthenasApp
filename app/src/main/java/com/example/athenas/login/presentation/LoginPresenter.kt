package com.example.athenas.login.presentation


import android.util.Patterns
import com.example.athenas.R
import com.example.athenas.login.Login
import com.example.athenas.login.data.LoginCallback
import com.example.athenas.login.data.LoginRepository


class LoginPresenter(

    var view: Login.View? = null,
    var repository: LoginRepository

): Login.Presenter {
    override fun login(email: String, password: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >= 8

        if (!isEmailValid) {
            view?.displayEmailFailure(R.string.invalid_email)
        } else {
            view?.displayEmailFailure(null)
        }
        if (!isPasswordValid) {
            view?.displayPasswordFailure(R.string.invalid_password)
        } else {
            view?.displayPasswordFailure(null)
        }

        if (isEmailValid && isPasswordValid) {
            repository.login(email, password, object: LoginCallback {
                override fun onSuccess() {
                    view?.onUserAuthenticated()
                }

                override fun onFailure(message: String) {
                    view?.onUserUnAuthorized(message)
                }

                override fun onComplete() {

                }
            })
        }

    }

    override fun onDestroy() {
        view = null
    }
}