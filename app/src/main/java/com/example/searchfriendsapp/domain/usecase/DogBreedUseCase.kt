package com.example.searchfriendsapp.domain.usecase

import com.example.searchfriendsapp.data.repository.DogsRepository

class DogBreedUseCase(private val repository: DogsRepository = DogsRepository()) {
    suspend operator fun invoke(breed: String) = repository.getDogsByBreed(breed)

}

//Aqui en el usecase va la logica del negocio

//como hago el mapeo entre modelos de datos  entre capas model domain y ui