package com.example.bottomnavigation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

import com.example.bottomnavigation.models.LoginCredentialsModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var loginState by mutableStateOf<String?>(null)
        private set

    var userToken by mutableStateOf<String?>(null)
        private set

    fun loginUser(credentials: LoginCredentialsModel) {
        viewModelScope.launch {
            try {
                // 👇 Aquí va la limpieza del correo
                val cleanedCredentials = credentials.copy(
                    email = credentials.email.trim(),
                    password = credentials.password
                )

                val response = RetrofitInstance.apiService.loginUser(cleanedCredentials)

                if (response.isSuccessful) {
                    response.body()?.let { tokenResponse ->
                        userToken = tokenResponse.token
                        loginState = "Login exitoso"
                        println("Token received: ${tokenResponse.token}")
                        println("User: ${tokenResponse.user.name}")
                    } ?: run {
                        loginState = "Error: Empty response body"
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    loginState = "Error: $errorBody"
                    println("Error response: $errorBody")
                }
            } catch (e: Exception) {
                loginState = "Error: ${e.message}"
                println("Exception: ${e.message}")
            }
        }
    }

    fun resetState() {
        loginState = null
        userToken = null
    }
}
