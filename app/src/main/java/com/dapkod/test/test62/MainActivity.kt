package com.dapkod.test.test62

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.StrictMode
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log.d
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.dapkod.test.test62.model.LoginResponse
import com.dapkod.test.test62.services.ApiServices
import com.dapkod.test.test62.services.SharedPref
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    val apiUrl : String = "http://test.webkotak.com/api/"

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = ContextCompat.getColor(this, R.color.red_700)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.hide()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val client = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
               level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                //HttpLoggingInterceptor.Level.BODY
            })
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val services: ApiServices = retrofit.create(ApiServices::class.java)

        val btnLogin = findViewById<Button>(R.id.btn_logout)
        btnLogin.setOnClickListener {
            val userInput = findViewById<EditText>(R.id.input_username)
            val passInput = findViewById<EditText>(R.id.input_password)
            var username = userInput.text.toString()
            var password = passInput.text.toString()

            services.loginProses(username,password).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if(response.code() == 200){
                        SharedPref(applicationContext).putData("akunName", response.body()?.data?.name.toString())
                        val loginPage = Intent(this@MainActivity, LoginActivity::class.java)
                        startActivityForResult(loginPage, 2022)
                    }else{
                        Toast.makeText(applicationContext,"Login Failed",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    d("XXXX",t.toString())
                }

            })

        }

    }
}