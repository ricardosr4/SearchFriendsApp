package com.example.searchfriendsapp.ui.activity.login.presenter

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.searchfriendsapp.data.response.UserData
import com.example.searchfriendsapp.databinding.ActivityLoginBinding
import com.example.searchfriendsapp.ui.activity.homeContainer.HomeContainerActivity
import com.example.searchfriendsapp.ui.activity.login.viewModel.LoginViewModel
import com.example.searchfriendsapp.ui.activity.preLogin.presenter.PreLoginActivity
import com.example.searchfriendsapp.util.AuthState

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
        setupLoginButton()
        setupObservers()
    }

    private fun setupObservers() {
        loginViewModel.loginState.observe(this) { state ->
            when (state) {
                is AuthState.Loading -> {
                    // falta ajustar una pantalla de carga
                }

                is AuthState.Success -> {
                    showToast(state.message)
                    startActivity(Intent(this, HomeContainerActivity::class.java))
                    finish()
                }

                is AuthState.Error -> {
                    showToast(state.error)
                }

                else -> { /* Estado Idle, sin acción */
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setupNavigation() {
        binding.ivBack.setOnClickListener {
            startActivity(Intent(this, PreLoginActivity::class.java))
        }
    }

    private fun setupLoginButton() {
        binding.cvLoginOpt2.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val user = UserData(email, password)

            loginViewModel.login(user)
        }
    }
}
