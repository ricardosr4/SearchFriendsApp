package com.example.searchfriendsapp.ui.activity.login.presenter

import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.searchfriendsapp.databinding.ActivityLoginBinding
import com.example.searchfriendsapp.ui.activity.homeContainer.HomeContainerActivity
import com.example.searchfriendsapp.ui.activity.preLogin.presenter.PreLoginActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        setupNavigation()
        setupLoginButton()
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

            if (isFormValid(email, password)) {
                loginUser(email, password)
            }
        }
    }

    private fun isFormValid(email: String, password: String): Boolean {
        return when {
            email.isBlank() -> {
                showToast("El correo electrónico es requerido")
                false
            }

            password.isBlank() -> {
                showToast("La contraseña es requerida")
                false
            }

            else -> true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    showToast("Inicio de sesión exitoso")
                    startActivity(Intent(this, HomeContainerActivity::class.java))
                    finish()
                } else {
                    showToast("Error: ${task.exception?.message}")
                }
            }
    }
}
