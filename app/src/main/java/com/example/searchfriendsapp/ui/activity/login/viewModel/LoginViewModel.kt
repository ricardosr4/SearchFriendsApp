package com.example.searchfriendsapp.ui.activity.login.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchfriendsapp.data.response.UserData
import com.example.searchfriendsapp.util.AuthState
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel():ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _loginState = MutableLiveData<AuthState>()
    val loginState: LiveData<AuthState> get() = _loginState

    fun login(user: UserData) {
        if (user.email.isBlank() || user.password.isBlank()) {
            _loginState.value = AuthState.Error("Los campos no pueden estar vacíos")
            return
        }

        _loginState.value = AuthState.Loading

        auth.signInWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginState.value = AuthState.Success("Inicio de sesión exitoso")
                } else {
                    _loginState.value = AuthState.Error(task.exception?.message ?: "Error desconocido")
                }
            }
    }
}