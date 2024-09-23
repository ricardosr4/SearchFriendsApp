package com.example.searchfriendsapp.ui.fragment.puppies.state

import com.example.searchfriendsapp.data.response.DogImageResponse

sealed class PuppiesState {
    data class Success(val success: DogImageResponse): PuppiesState()
    data class Error(val error: String): PuppiesState()
    data object Loading : PuppiesState()
}
