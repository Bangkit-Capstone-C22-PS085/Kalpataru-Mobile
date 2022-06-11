package com.akhmadkhasan68.kalpataru.ui.main

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.akhmadkhasan68.kalpataru.R
import com.akhmadkhasan68.kalpataru.databinding.ActivityMainBinding
import com.akhmadkhasan68.kalpataru.model.UserPreference
import com.akhmadkhasan68.kalpataru.ui.ViewModelFactory
import com.akhmadkhasan68.kalpataru.ui.camera.CameraActivity
import com.akhmadkhasan68.kalpataru.ui.login.LoginActivity
import com.akhmadkhasan68.kalpataru.ui.onborading.OnboardingActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideSystemUI()

        val navView: BottomNavigationView = binding.navView

        navView.background = null
        navView.menu.getItem(2).isEnabled = false

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_sell,
                R.id.navigation_history,
                R.id.navigation_profile
            )
        )

        binding.scanButton.setOnClickListener(){
            val intentToCamera = Intent(this@MainActivity, CameraActivity::class.java)
            startActivity(intentToCamera)
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        setupViewModel()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[MainViewModel::class.java]

        mainViewModel.getUser().observe(this, {user ->
            if(!user.isLogin) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        })
    }

    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
}