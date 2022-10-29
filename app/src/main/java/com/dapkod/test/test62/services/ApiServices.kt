package com.dapkod.test.test62.services

import com.dapkod.test.test62.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServices {
    @FormUrlEncoded
    @POST("login")
    fun loginProses(@Field("username") username: String, @Field("password") password: String): Call<LoginResponse>
}
