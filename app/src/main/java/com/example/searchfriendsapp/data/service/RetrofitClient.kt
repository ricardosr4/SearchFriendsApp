package com.example.searchfriendsapp.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    private const val BASE_URL = "https://dog.ceo/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val dogService = retrofit.create<DogsService>()

    fun getDog() : DogsService{
        return dogService
    }
}