package com.example.searchfriendsapp.data.service

import com.example.searchfriendsapp.data.response.DogResponse
import com.example.searchfriendsapp.data.response.DogsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsService {
    @GET("breeds/image/random")
    suspend fun getDog(): Response<DogResponse>

    @GET("breeds/image/random/50")
    suspend fun getDogs(): Response<DogsResponse>

    @GET("breed/{breed}/images/random")
    suspend fun getDogByBreed(@Path("breed") breed: String): Response<DogResponse>

}