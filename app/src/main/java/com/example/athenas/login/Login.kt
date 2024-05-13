package com.example.athenas.login

import com.example.athenas.common.base.BasePresenter
import com.example.athenas.common.base.BaseView

interface Login {

    interface Presenter: BasePresenter {
        fun login(email: String, password: String)
    }
    interface View: BaseView<Presenter> {
        fun displayEmailFailure(emailError: Int?)
        fun displayPasswordFailure(passwordError: Int?)
        fun showProgress(enabled: Boolean)
        fun onUserAuthenticated()
        fun onUserUnAuthorized(message: String)
    }
}