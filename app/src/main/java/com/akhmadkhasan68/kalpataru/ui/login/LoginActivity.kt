package com.akhmadkhasan68.kalpataru.ui.login

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.akhmadkhasan68.kalpataru.databinding.ActivityLoginBinding
import com.akhmadkhasan68.kalpataru.model.UserPreference
import com.akhmadkhasan68.kalpataru.ui.ViewModelFactory
import com.akhmadkhasan68.kalpataru.ui.main.MainActivity
import com.akhmadkhasan68.kalpataru.ui.register.RegisterActivity

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel : LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        setupActions()
    }

    private fun setupView() {
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

    private fun setupViewModel() {
        loginViewModel = ViewModelProvider(this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[LoginViewModel::class.java]

        loginViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        loginViewModel.errorMessage.observe(this, { message ->
            Toast.makeText(this@LoginActivity, message.toString(), Toast.LENGTH_SHORT).show()
        })

        loginViewModel.getUser().observe(this, {user ->
            if(user.isLogin) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })
    }

    private fun setupActions() {
        binding.txtRegisterNow.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            actionLogin()
        }
    }

    private fun actionLogin() {
        val username = binding.editTextUsername
        val password = binding.editTextPassword

        if(checkValidation(username, password)){
            loginViewModel.login(username.text.toString().trim(), password.text.toString().trim())
        }
    }

    private fun checkValidation(username: EditText, password: EditText) : Boolean {
        val isUsernameError : Boolean = username.length() == 0
        val isPasswordError : Boolean = password.length() == 0
        val isError : Boolean = isUsernameError || isPasswordError

        if(isError){
            if(isUsernameError){
                username.setError("Username harus diisi!")
            }
            if(isPasswordError){
                password.setError("Password harus diisi!")
            }

            return false
        }

        return true
    }

    private fun showLoading(isLoading: Boolean) {
        if(isLoading){
            binding.loading.visibility = View.VISIBLE
        }else{
            binding.loading.visibility = View.GONE
        }
    }
}