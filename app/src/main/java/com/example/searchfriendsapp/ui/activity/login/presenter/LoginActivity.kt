package com.example.searchfriendsapp.ui.activity.login.presenter

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.searchfriendsapp.data.response.UserData
import com.example.searchfriendsapp.databinding.ActivityLoginBinding
import com.example.searchfriendsapp.ui.activity.homeContainer.HomeContainerActivity
import com.example.searchfriendsapp.ui.activity.login.viewModel.LoginViewModel
import com.example.searchfriendsapp.ui.activity.preLogin.presenter.PreLoginActivity
import com.example.searchfriendsapp.util.AuthState
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel>()

//    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        auth = FirebaseAuth.getInstance()

        setupNavigation()
        setupLoginButton()
        setupObservers()
    }
    private fun setupObservers() {
        loginViewModel.loginState.observe(this, Observer { state ->
            when (state) {
                is AuthState.Loading -> {
                    // Muestra un loader o deshabilita el botón de login
                }
                is AuthState.Success -> {
                    showToast(state.message)
                    startActivity(Intent(this, HomeContainerActivity::class.java))
                    finish()
                }
                is AuthState.Error -> {
                    showToast(state.error)
                }
                else -> { /* Estado Idle, sin acción */ }
            }
        })
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
//    private fun isFormValid(email: String, password: String): Boolean {
//        return when {
//            email.isBlank() -> {
//                showToast("El correo electrónico es requerido")
//                false
//            }
//
//            password.isBlank() -> {
//                showToast("La contraseña es requerida")
//                false
//            }
//
//            else -> true
//        }
//    }

//    private fun showToast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    }
//    private fun loginUser(email: String, password: String) {
//        auth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    showToast("Inicio de sesión exitoso")
//                    startActivity(Intent(this, HomeContainerActivity::class.java))
//                    finish()
//                } else {
//                    showToast("Error: ${task.exception?.message}")
//                }
//            }
//    }
}
