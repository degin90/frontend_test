package com.dapkod.test.test62

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.dapkod.test.test62.services.SharedPref


class LoginActivity : AppCompatActivity() {

    @SuppressLint("SourceLockedOrientationActivity", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)
        window.statusBarColor = ContextCompat.getColor(this, R.color.red_700)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.hide()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        val loginAkun = SharedPref(applicationContext).getString("akunName")
        val wellcomeText = findViewById<TextView>(R.id.wellcome_text)

        wellcomeText.text = "Wellcome ! $loginAkun"
        val btnLogout = findViewById<Button>(R.id.btn_logout)
        btnLogout.setOnClickListener {
            SharedPref(applicationContext).removeAllData()
            val loginPage = Intent(this, MainActivity::class.java)
            startActivityForResult(loginPage, 5050)
        }

    }
}