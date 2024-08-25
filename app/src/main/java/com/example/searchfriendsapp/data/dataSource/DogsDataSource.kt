package com.example.searchfriendsapp.data.dataSource

import com.example.searchfriendsapp.data.response.DogResponse
import com.example.searchfriendsapp.data.service.RetrofitClient
import retrofit2.Response

class DogsDataSource {
    private val instance = RetrofitClient.getDog()
    private val instance2 = RetrofitClient.getDogs()



    suspend fun getDog(): Response<DogResponse> {
        return instance.getDog()
    }
    suspend fun getDogs(): Response<DogResponse> {
        return instance2.getDogs()
    }
}