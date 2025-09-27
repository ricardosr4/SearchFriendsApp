package com.example.searchfriendsapp.ui.activity.register.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchfriendsapp.data.response.UserData
import com.example.searchfriendsapp.util.AuthState
import com.google.firebase.auth.FirebaseAuth

class RegisterViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _registerState = MutableLiveData<AuthState>()
    val registerState: LiveData<AuthState> get() = _registerState

    fun register(user: UserData) {
        if (user.email.isBlank() || user.password.isBlank()) {
            _registerState.value = AuthState.Error("Los campos no pueden estar vacíos")
            return
        }

        if (user.password.length < 6) {
            _registerState.value = AuthState.Error("La contraseña debe tener al menos 6 caracteres")
            return
        }

        _registerState.value = AuthState.Loading

        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _registerState.value = AuthState.Success("Registro exitoso")
                } else {
                    _registerState.value =
                        AuthState.Error(task.exception?.message ?: "Error desconocido")
                }
            }
    }

}