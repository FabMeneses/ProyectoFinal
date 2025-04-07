package com.example.bottomnavigation.models

// Simplified models to match your actual API response
data class TokenResponse(
    val token: String,  // Just a String, not a Token object
    val user: User
)

data
 class User(
    val id: Int,
    val name: String,
    val email: String,
    val created_at: String,
    val updated_at: String
)