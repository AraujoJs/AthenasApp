package com.example.athenas.login.data

import android.os.Handler
import android.os.Looper
import com.example.athenas.common.model.Datasource


class LoginFakeDatabase: LoginDatabase {
    override fun login(email: String, password: String, callback: LoginCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Datasource.userAuth.firstOrNull {
                email == it.email
            }
            when {
                userAuth == null -> callback.onFailure("User not found")
                userAuth.password != password -> callback.onFailure("Wrong password")
                else -> {
                    Datasource.sessionAuth = userAuth
                    callback.onSuccess()
                }
            }
        }, 1000)
        callback.onComplete()
    }
}