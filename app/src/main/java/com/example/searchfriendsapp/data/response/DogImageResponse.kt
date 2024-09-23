package com.example.searchfriendsapp.data.response

import android.hardware.biometrics.BiometricManager.Strings
import com.google.gson.annotations.SerializedName

class DogImageResponse(
    @SerializedName("message")
    val message: List<String>?,

    @SerializedName("status")
    val status: String?
)