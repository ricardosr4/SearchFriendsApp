package com.example.searchfriendsapp.ui.fragment.random.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchfriendsapp.data.repository.DogsRepository
import com.example.searchfriendsapp.ui.fragment.random.state.RandomState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RandomViewModel(private val repository: DogsRepository = DogsRepository()) : ViewModel() {

    private val _randomState = MutableLiveData<RandomState>()
    val randomState: MutableLiveData<RandomState> = _randomState

    fun getRandomDog() {
        CoroutineScope(Dispatchers.IO).launch {
            _randomState.postValue(RandomState.Loading)
            val response = repository.getDog()
            if (response.isSuccessful) {
                response.body()?.let {
                    _randomState.postValue(RandomState.Success(it))
                } ?: _randomState.postValue(RandomState.Error("Error de Servicio"))
            } else {
                _randomState.postValue(RandomState.Error("Error en el Servidor"))
            }
        }
    }
}