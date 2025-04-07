package com.example.bottomnavigation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.bottomnavigation.models.UserModel
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    var registrationState by mutableStateOf<String?>(null)
        private set

    fun registerUser(user: UserModel) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.apiService.registerUser(user)
                if (response.isSuccessful && response.body() != null) {
                    registrationState = "Registro exitoso"
                } else {
                    registrationState = "Error: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                registrationState = "Excepción: ${e.localizedMessage}"
            }
        }
    }

    fun resetState() {
        registrationState = null
    }
}
