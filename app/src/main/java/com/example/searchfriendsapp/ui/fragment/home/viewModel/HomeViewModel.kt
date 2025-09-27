package com.example.searchfriendsapp.ui.fragment.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchfriendsapp.data.repository.DogsRepository
import com.example.searchfriendsapp.domain.usecase.DogsListUseCase
import com.example.searchfriendsapp.ui.fragment.home.State.HomeState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: DogsListUseCase = DogsListUseCase()) :ViewModel(){

    private val _homeState = MutableLiveData<HomeState>()
    val homeState: LiveData<HomeState> = _homeState

    fun getListDogs() {
        CoroutineScope(Dispatchers.IO).launch {
            _homeState.postValue(HomeState.Loading)
                val response = useCase.invoke()
                if (response.isSuccessful) {
                    response.body()?.let {
                        _homeState.postValue(HomeState.Success(it))
                    } ?: _homeState.postValue(HomeState.Error("Error en los datos"))
                } else {
                    _homeState.postValue(HomeState.Error("Error en la respuesta"))
                }

        }
    }
}