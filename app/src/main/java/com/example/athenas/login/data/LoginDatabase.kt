package com.example.athenas.login.data

interface LoginDatabase {
    fun login(email: String, password: String, callback: LoginCallback)
}