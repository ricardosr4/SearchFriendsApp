package com.example.searchfriendsapp.ui.fragment.puppies.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchfriendsapp.data.repository.DogsRepository
import com.example.searchfriendsapp.data.response.DogImageResponse
import com.example.searchfriendsapp.ui.fragment.puppies.state.PuppiesState
import kotlinx.coroutines.launch

class DogViewModel : ViewModel() {
    private val repository = DogsRepository()

    private val _images = MutableLiveData<PuppiesState?>()
    val images: LiveData<PuppiesState?> get() = _images
//
//    init {
//        fetchDogImages()
//    }

    fun fetchDogImages(){
        _images.value = PuppiesState.Loading // Estado de carga

        viewModelScope.launch {
            try {
                val response = repository.getRandomDogImages()
                _images.value = PuppiesState.Success(DogImageResponse(response.message, response.status)) //Estado de éxito

            } catch (e: Exception){
                _images.value = PuppiesState.Error(e.message?: "Error desconocido") //Estado de eror
            }
        }
    }

}