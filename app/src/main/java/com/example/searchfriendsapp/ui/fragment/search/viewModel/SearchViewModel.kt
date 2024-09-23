package com.example.searchfriendsapp.ui.fragment.search.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchfriendsapp.data.repository.DogsRepository
import com.example.searchfriendsapp.domain.usecase.DogBreedUseCase
import com.example.searchfriendsapp.ui.fragment.search.state.SearchState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(private val useCase :DogBreedUseCase = DogBreedUseCase()):ViewModel() {

    private val _searchState = MutableLiveData<SearchState>()
    val searchState: MutableLiveData<SearchState> = _searchState

    fun searchDogByBreed(breed: String) {
        CoroutineScope(Dispatchers.IO).launch {
            _searchState.postValue(SearchState.Loading)
            val response = useCase.invoke(breed)
            if (response.isSuccessful) {
                response.body()?.let {
                    _searchState.postValue(SearchState.Success(it))
                } ?: _searchState.postValue(SearchState.Error("Error de Servicio"))
            } else {
                _searchState.postValue(SearchState.Error("Error en el Servidor"))
            }
        }
    }
}