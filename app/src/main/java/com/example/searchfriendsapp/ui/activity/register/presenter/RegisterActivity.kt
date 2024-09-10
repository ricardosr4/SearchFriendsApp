package com.example.searchfriendsapp.ui.activity.register.presenter

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.searchfriendsapp.databinding.ActivityRegisterBinding
import com.example.searchfriendsapp.ui.activity.login.presenter.LoginActivity
import com.example.searchfriendsapp.ui.activity.preLogin.presenter.PreLoginActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        setupNavigation()

        binding.cvRegister.setOnClickListener {
            val fullName = binding.etFullName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (isFormValid(fullName, email, password)) {
                registerUser(email, password)
            }
        }
    }
    private fun setupNavigation() {
        binding.backButton.setOnClickListener {
            startActivity(Intent(this, PreLoginActivity::class.java))
        }
    }

    private fun isFormValid(fullName: String, email: String, password: String): Boolean {
        when {
            fullName.isBlank() -> {
                showToast("El nombre completo es requerido")
                return false
            }

            email.isBlank() -> {
                showToast("El correo electrónico es requerido")
                return false
            }

            password.isBlank() -> {
                showToast("La contraseña es requerida")
                return false
            }

            password.length < 6 -> {
                showToast("La contraseña debe tener al menos 6 caracteres")
                return false
            }

            else -> return true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    showToast("Registro exitoso")
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    showToast("Error: ${task.exception?.message}")
                }
            }
    }
}
