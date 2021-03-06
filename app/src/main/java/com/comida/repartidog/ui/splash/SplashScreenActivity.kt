package com.comida.repartidog.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.comida.repartidog.MainActivity
import com.comida.repartidog.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            startActivity(
                Intent(this@SplashScreenActivity, MainActivity::class.java)
            )
            finish()
        }, 1500)

    }
}