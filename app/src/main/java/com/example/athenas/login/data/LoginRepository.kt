package com.example.athenas.login.data

class LoginRepository(private val database: LoginDatabase) {
    fun login(email: String, password: String, callback: LoginCallback) {
        database.login(email, password, callback)
    }
}