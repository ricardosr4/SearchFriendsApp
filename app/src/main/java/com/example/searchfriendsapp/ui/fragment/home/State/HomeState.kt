package com.example.searchfriendsapp.ui.fragment.home.State

import com.example.searchfriendsapp.data.response.DogResponse

sealed class HomeState {
    data class Success(val success: DogResponse) : HomeState()
    data class Error(val error: String) : HomeState()
    data object Loading : HomeState()
}