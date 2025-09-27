package com.example.searchfriendsapp.ui.activity.register.presenter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.searchfriendsapp.data.response.UserData
import com.example.searchfriendsapp.databinding.ActivityRegisterBinding
import com.example.searchfriendsapp.ui.activity.login.presenter.LoginActivity
import com.example.searchfriendsapp.ui.activity.register.viewModel.RegisterViewModel
import com.example.searchfriendsapp.util.AuthState

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupNavigation()
        setupRegisterButton()
    }

    private fun setupObservers() {
        registerViewModel.registerState.observe(this) { state ->
            when (state) {
                is AuthState.Loading -> {
                    showLoading()
                }

                is AuthState.Success -> {
                    showToast(state.email)
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }

                is AuthState.Error -> {
                    hideLoading()
                    showToast(state.error)
                }

                else -> {
                    hideLoading()
                    showToast("Error")
                }
            }
        }
    }
    private fun setupNavigation() {
        binding.backButton.setOnClickListener {
            finish()
//            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun setupRegisterButton() {
        binding.cvRegister.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val user = UserData(email, password)

            registerViewModel.register(user)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun showLoading() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressCircular.visibility = View.GONE
    }

}
