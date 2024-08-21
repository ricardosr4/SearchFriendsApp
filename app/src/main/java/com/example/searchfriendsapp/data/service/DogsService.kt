package com.example.searchfriendsapp.data.service

import com.example.searchfriendsapp.data.response.DogResponse
import retrofit2.Response
import retrofit2.http.GET

interface DogsService {
    @GET("breeds/image/random")
    suspend fun getDog(): Response<DogResponse>
}