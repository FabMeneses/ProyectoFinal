package com.example.bottomnavigation.models

import com.google.gson.annotations.SerializedName

data class LoginCredentialsModel(
    @SerializedName("email")
    val email: String, // Asegúrate que no tenga espacios

    @SerializedName("password")
    val password: String
)



