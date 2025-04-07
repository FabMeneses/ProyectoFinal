package com.example.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigation.screen.BiometricScreen
import com.example.bottomnavigation.screen.LoginScreen
import com.example.bottomnavigation.screen.RegisterScreen
import com.example.bottomnavigation.screen.VerifyCodeScreen
import com.example.bottomnavigation.screen.WelcomeScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "WelcomeScreen"
    ) {
        // 👇 ESTA es la pantalla de biometría
        composable("BiometricScreen") {
            BiometricScreen(
                navController = navController
            )
        }

        composable("LoginScreen") {
            LoginScreen(
                navController = navController,
                onLoginSuccess = {
                    navController.navigate("BiometricScreen") {
                        popUpTo("LoginScreen") { inclusive = true }
                        popUpTo("RegisterScreen") { inclusive = true }
                        popUpTo("WelcomeScreen") { inclusive = true }
                    }
                }
            )
        }

        composable("RegisterScreen") {
            RegisterScreen(
                navController = navController,
                onRegisterSuccess = {
                    navController.navigate("VerifyCodeScreen") {
                        popUpTo("LoginScreen") { inclusive = true }
                        popUpTo("RegisterScreen") { inclusive = true }
                    }
                }
            )
        }

        composable("VerifyCodeScreen") {
            VerifyCodeScreen(
                navController = navController,
                onVerifyCodeSuccess = {
                    navController.navigate("MainScreen") {
                        popUpTo("LoginScreen") { inclusive = true }
                        popUpTo("RegisterScreen") { inclusive = true }
                    }
                }
            )
        }

        composable("WelcomeScreen") {
            WelcomeScreen(
                onWelcomeSuccess = {
                    navController.navigate("LoginScreen") {
                        popUpTo("RegisterScreen") { inclusive = true }
                        popUpTo("LoginScreen") { inclusive = true }
                        popUpTo("WelcomeScreen") { inclusive = true }
                    }
                }
            )
        }

        composable("MainScreen") {
            MainScreen(navController = navController)
        }
    }
}
