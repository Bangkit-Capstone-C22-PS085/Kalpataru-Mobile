package com.akhmadkhasan68.kalpataru.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.akhmadkhasan68.kalpataru.R
import com.akhmadkhasan68.kalpataru.model.UserPreference
import com.akhmadkhasan68.kalpataru.ui.ViewModelFactory
import com.akhmadkhasan68.kalpataru.ui.main.MainActivity
import com.akhmadkhasan68.kalpataru.ui.onborading.OnboardingActivity

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class SplashActivity : AppCompatActivity() {
    private lateinit var splashViewModel: SplashViewModel

    companion object{
        const val MILIS_TIME : Long = 2000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setupViewModel()
        setupView()
    }

    private fun setupViewModel() {
        splashViewModel = ViewModelProvider(this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[SplashViewModel::class.java]

        splashViewModel.getUser().observe(this, {user ->
            if(user.isLogin) {
                redirectMain()
            }else{
                redirectOnBoarding()
            }
        })
    }

    private fun redirectOnBoarding() {
        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
            finish()
        }, MILIS_TIME)
    }

    private fun redirectMain() {
        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, MILIS_TIME)
    }

    private fun setupView() {
        supportActionBar?.hide()
    }
}