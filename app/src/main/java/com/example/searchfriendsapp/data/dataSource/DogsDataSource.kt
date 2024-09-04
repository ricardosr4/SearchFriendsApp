package com.example.searchfriendsapp.data.dataSource

import com.example.searchfriendsapp.data.response.DogResponse
import com.example.searchfriendsapp.data.response.DogsResponse
import com.example.searchfriendsapp.data.service.DogsService
import com.example.searchfriendsapp.data.service.RetrofitClient
import retrofit2.Response

class DogsDataSource(private val dogService: DogsService = RetrofitClient.dogService) {
    suspend fun getDog(): Response<DogResponse> {
        return dogService.getDog()
    }
    suspend fun getDogs(): Response<DogsResponse> {
        return dogService.getDogs()
    }
    suspend fun getDogsByBreed(breed: String): Response<DogResponse> {
        return dogService.getDogByBreed(breed)
    }

}