package com.example.searchfriendsapp.data.repository

import com.example.searchfriendsapp.data.dataSource.DogsDataSource
import com.example.searchfriendsapp.data.response.DogResponse
import retrofit2.Response

class DogsRepository(private val dogService: DogsDataSource = DogsDataSource()) {
    suspend fun getDog(): Response<DogResponse> {
        return dogService.getDog()
    }
    suspend fun getDogs(): Response<DogResponse> {
        return dogService.getDogs()
    }
}