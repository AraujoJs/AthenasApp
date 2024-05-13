package com.example.athenas.common.base

import com.example.athenas.login.data.LoginFakeDatabase
import com.example.athenas.login.data.LoginRepository


object DependencyInjector {
    fun loginRepository(): LoginRepository {
        return LoginRepository(LoginFakeDatabase())
    }
}