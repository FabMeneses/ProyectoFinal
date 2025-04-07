package com.example.bottomnavigation.apiService


import com.example.bottomnavigation.models.LoginCredentialsModel
import com.example.bottomnavigation.models.TokenResponse
import com.example.bottomnavigation.models.UserModel
import com.example.bottomnavigation.models.UserResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/users")
    suspend fun registerUser(@Body user: UserModel): Response<UserResponseModel>

    @POST("api/login")
    suspend fun loginUser(
        @Body credentials: LoginCredentialsModel
    ): Response<TokenResponse>
}
