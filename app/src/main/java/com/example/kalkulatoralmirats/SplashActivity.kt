package com.example.kalkulatoralmirats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 3000 // Durasi splash screen dalam milidetik (3 detik)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            // Aksi yang akan dilakukan setelah splash screen selesai
            val intent = Intent(this, MainActivity::class.java) // Ganti dengan activity tujuan setelah splash screen
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)
    }
}