package com.example.searchfriendsapp.util

sealed class AuthState {
    data object Loading : AuthState()
    data class Success(val email: String) : AuthState()
    data class Error(val error: String) : AuthState()

}