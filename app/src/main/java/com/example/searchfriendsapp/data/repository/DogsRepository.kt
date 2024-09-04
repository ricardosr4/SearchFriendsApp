package com.example.searchfriendsapp.data.repository

import com.example.searchfriendsapp.data.dataSource.DogsDataSource
import com.example.searchfriendsapp.data.response.DogResponse
import com.example.searchfriendsapp.data.response.DogsResponse
import retrofit2.Response

class DogsRepository(private val dogDataSource: DogsDataSource = DogsDataSource()) {
    suspend fun getDog(): Response<DogResponse> {
        return dogDataSource.getDog()
    }
    suspend fun getDogs(): Response<DogsResponse> {
        return dogDataSource.getDogs()
    }
    suspend fun getDogsByBreed(breed: String): Response<DogResponse> {
        return dogDataSource.getDogsByBreed(breed)
    }
}