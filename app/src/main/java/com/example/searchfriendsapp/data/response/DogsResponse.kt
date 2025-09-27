package com.example.searchfriendsapp.data.response

import com.google.gson.annotations.SerializedName

data class DogsResponse(
    @SerializedName("message")
    val message: List<String>?,

    @SerializedName("status")
    val status: String?
)
