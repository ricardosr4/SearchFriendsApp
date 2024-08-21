package com.example.searchfriendsapp.ui.fragment.random.state

import com.example.searchfriendsapp.data.response.DogResponse

sealed class RandomState {

    data class Success(val success: DogResponse) : RandomState()
    data class Error(val error: String) : RandomState()
    data object Loading : RandomState()
}