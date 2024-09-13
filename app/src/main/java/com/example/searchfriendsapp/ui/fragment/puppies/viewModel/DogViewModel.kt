package com.example.searchfriendsapp.ui.fragment.puppies.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchfriendsapp.data.repository.DogsRepository
import kotlinx.coroutines.launch

class DogViewModel : ViewModel() {
    private val repository = DogsRepository()

    private val _images = MutableLiveData<List<String>>()
    val images: LiveData<List<String>> get() = _images

    init {
        fetchDogImages()
    }

    private fun fetchDogImages(){
        viewModelScope.launch {
            try {
                val response = repository.getRandomDogImages()
                _images.value = response.message
            } catch (e: Exception){
                _images.value = emptyList() //Fallback en caso de error
            }
        }
    }

}