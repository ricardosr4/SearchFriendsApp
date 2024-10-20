package com.example.searchfriendsapp.util

sealed class AuthState {
    data object Loading : AuthState()
    data class Success(val message: String) : AuthState()
    data class Error(val error: String) : AuthState()
    data object Idle : AuthState() // Estado inicial o sin acción
}