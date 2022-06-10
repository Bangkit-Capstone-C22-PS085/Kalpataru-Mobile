package com.akhmadkhasan68.kalpataru.ui.register

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.*
import androidx.activity.viewModels
import com.akhmadkhasan68.kalpataru.R
import com.akhmadkhasan68.kalpataru.databinding.ActivityRegisterBinding
import com.akhmadkhasan68.kalpataru.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel : RegisterViewModel by viewModels()
    private lateinit var role : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupActions()
        setupViewModel()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text: String = parent?.getItemAtPosition(position).toString()
        if(text == "Pengepul"){
            role = "OPERATOR"
        }else{
            role = "MEMBER"
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun setupViewModel() {
        registerViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        registerViewModel.errorMessage.observe(this, { message ->
            Toast.makeText(this@RegisterActivity, message.toString(), Toast.LENGTH_SHORT).show()
        })

        registerViewModel.isSuccess.observe(this, {
            if(it){
                Toast.makeText(this, "Selamat anda berhasil mendaftar", Toast.LENGTH_SHORT)
                startActivity(Intent(this, LoginActivity::class.java))
            }
        })
    }

    private fun setupActions() {
        binding.txtLoginNow.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {
            actionRegister()
        }

        binding.roleOptions.onItemSelectedListener = this
    }

    private fun actionRegister() {
        val name = binding.editTextName
        val username = binding.editTextUsername
        val email = binding.editTextEmail
        val password = binding.editTextPassword
        val password_confirm = binding.editTextPasswordConfirmation
        val role = role

        if(checkValidation(name, username, email, password, password_confirm, role)){
            registerViewModel.register(
                username.text.toString(),
                email.text.toString(),
                name.text.toString(),
                password.text.toString(),
                password_confirm.text.toString(),
                role
            )
        }
    }

    private fun checkValidation(name: EditText, username: EditText, email: EditText, password: EditText, passwordConfirm: EditText, role: String): Boolean {
        val isNameError : Boolean = name.length() == 0
        val isUsernameError : Boolean = username.length() == 0
        val isEmailError : Boolean = email.length() == 0
        val isPasswordError : Boolean = password.length() == 0
        val isPasswordConfirmError : Boolean = passwordConfirm.length() == 0
        val isPasswordNotMatch : Boolean = !(password.text.trim() == passwordConfirm.text.trim())
        val isRoleError : Boolean = role.length == 0

        Log.d("password confirmation", isPasswordNotMatch.toString())

        if(isNameError || isUsernameError || isEmailError || isPasswordError || isPasswordConfirmError || isPasswordNotMatch || isRoleError){
            if(isNameError){
                name.setError("Nama harus diisi")
            }

            if(isUsernameError){
                username.setError("Username harus diisi")
            }

            if(isEmailError){
                email.setError("Email harus diisi")
            }

            if(isPasswordError){
                password.setError("Password harus diisi")
            }

            if(isPasswordConfirmError){
                passwordConfirm.setError("Konfirmasi password harus diisi")
            }else if(isPasswordNotMatch){
                passwordConfirm.setError("Konfirmasi password tidak sesuai")
            }

            return false
        }

        return true
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

        val spinner : Spinner = binding.roleOptions
        ArrayAdapter.createFromResource(
            this,
            R.array.roles_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if(isLoading){
            binding.loading.visibility = View.VISIBLE
        }else{
            binding.loading.visibility = View.GONE
        }
    }
}

