package com.example.searchfriendsapp.domain.usecase

import com.example.searchfriendsapp.data.repository.DogsRepository

class DogsListUseCase(private val repository: DogsRepository = DogsRepository()) {
    suspend operator fun invoke() = repository.getDogs()

}