package com.example.searchfriendsapp.data.response

import com.google.gson.annotations.SerializedName
import com.google.rpc.Status

data class DogImageResponse(
    @SerializedName("message")
    val message: List<String>?,

    @SerializedName("status")
    val status: String
)