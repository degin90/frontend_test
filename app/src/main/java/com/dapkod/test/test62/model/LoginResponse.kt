package com.dapkod.test.test62.model

data class LoginResponse(
    val `data`: DataUserLogin,
    val token: String
)

data class DataUserLogin(
    val address: String,
    val email: String,
    val name: String,
    val phone: String
)