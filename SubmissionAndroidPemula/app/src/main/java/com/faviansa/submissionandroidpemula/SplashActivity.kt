package com.faviansa.submissionandroidpemula

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }
        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
            // Start main activity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1500)
    }
}