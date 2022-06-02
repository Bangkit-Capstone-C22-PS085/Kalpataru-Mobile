package com.akhmadkhasan68.kalpataru.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.akhmadkhasan68.kalpataru.MainActivity
import com.akhmadkhasan68.kalpataru.R
import com.akhmadkhasan68.kalpataru.ui.onborading.OnboardingActivity

class SplashActivity : AppCompatActivity() {
    companion object{
        const val MILIS_TIME : Long = 2000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
            finish()
        }, MILIS_TIME)
    }
}