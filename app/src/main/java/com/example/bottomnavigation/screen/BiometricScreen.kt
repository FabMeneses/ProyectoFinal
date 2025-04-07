package com.example.bottomnavigation.screen

import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import java.util.concurrent.Executors

fun Context.findFragmentActivity(): FragmentActivity? {
    var ctx = this
    while (ctx is ContextWrapper) {
        if (ctx is FragmentActivity) return ctx
        ctx = ctx.baseContext
    }
    return null
}

@Composable
fun BiometricScreen(navController: NavController) {
    val context = LocalContext.current
    val isAuthenticated = remember { mutableStateOf(false) }

    val biometricManager = BiometricManager.from(context)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
                    BiometricManager.BIOMETRIC_SUCCESS -> {
                        context.findFragmentActivity()?.let { activity ->
                            authenticateWithBiometrics(activity, isAuthenticated) {
                                // 👇 AQUÍ va la navegación tras autenticar
                                navController.navigate("MainScreen") {
                                    popUpTo("BiometricScreen") { inclusive = true }
                                }
                            }
                        } ?: Toast.makeText(context, "No FragmentActivity found", Toast.LENGTH_SHORT).show()
                    }
                    BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                        Toast.makeText(context, "No hay huellas registradas", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(context, "Autenticación no disponible", Toast.LENGTH_SHORT).show()
                    }
                }
            }) {
                Text("Autenticar")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = if (isAuthenticated.value) "¡Autenticado!" else "Por favor autentícate",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
            )
        }
    }
}

fun authenticateWithBiometrics(
    activity: FragmentActivity,
    isAuthenticated: MutableState<Boolean>,
    onSuccess: () -> Unit
) {
    val executor = Executors.newSingleThreadExecutor()

    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Autenticación biométrica")
        .setSubtitle("Usa tu huella digital para continuar")
        .setNegativeButtonText("Cancelar")
        .build()

    val biometricPrompt = BiometricPrompt(
        activity,
        executor,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                isAuthenticated.value = true
                activity.runOnUiThread {
                    onSuccess() // 👈 Navega aquí
                }
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                activity.runOnUiThread {
                    Toast.makeText(activity, "Autenticación fallida", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                activity.runOnUiThread {
                    Toast.makeText(activity, "Error: $errString", Toast.LENGTH_SHORT).show()
                }
            }
        })

    biometricPrompt.authenticate(promptInfo)
}

@Preview(showBackground = true)
@Composable
fun PreviewBiometricScreen() {
    val navController = rememberNavController()
    BiometricScreen(navController = navController)
}
