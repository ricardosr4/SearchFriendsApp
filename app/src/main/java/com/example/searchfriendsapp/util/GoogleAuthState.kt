package com.example.searchfriendsapp.util

sealed class GoogleAuthState {
    data object Loading : GoogleAuthState()
    data class Success(val email: String) : GoogleAuthState()
    data class Error(val message: String) : GoogleAuthState()
}