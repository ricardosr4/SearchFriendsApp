package com.example.searchfriendsapp.ui.fragment.puppies.state

import com.example.searchfriendsapp.data.response.DogResponse

sealed class DogImageState {
    data class Success(val success: DogResponse): DogImageState()

    data class Error(val error: String): DogImageState()

    data object Loading : DogImageState()
}