package com.example.searchfriendsapp.ui.fragment.search.state

import com.example.searchfriendsapp.data.response.DogResponse

sealed class SearchState {
    data class Success(val data: DogResponse) : SearchState()
    data class Error(val message: String) : SearchState()
    data object Loading : SearchState()


}