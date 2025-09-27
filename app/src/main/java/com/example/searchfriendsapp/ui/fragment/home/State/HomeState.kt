package com.example.searchfriendsapp.ui.fragment.home.State


import com.example.searchfriendsapp.data.response.DogsResponse

sealed class HomeState {
    data class Success(val success: DogsResponse) : HomeState()
    data class Error(val error: String) : HomeState()
    data object Loading : HomeState()
}