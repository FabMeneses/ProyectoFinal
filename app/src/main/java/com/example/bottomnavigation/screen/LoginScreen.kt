package com.example.bottomnavigation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bottomnavigation.R
import com.example.bottomnavigation.components.EmailOutlinedField
import com.example.bottomnavigation.components.LinkText
import com.example.bottomnavigation.components.Message
import com.example.bottomnavigation.components.OrDivider
import com.example.bottomnavigation.components.PasswordOutlinedField
import com.example.bottomnavigation.components.PrimaryButton
import com.example.bottomnavigation.components.SocialIconButton
import com.example.bottomnavigation.models.LoginCredentialsModel
import com.example.bottomnavigation.viewmodels.LoginViewModel
import com.example.bottomnavigation.ui.theme.Brown

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = viewModel(),
    onLoginSuccess: () -> Unit
) {
    val loginState = viewModel.loginState
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Fondo decorativo superior
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                // Logo/Imagen
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(120.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Título
                Text(
                    text = "Welcome Back",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Subtítulo
                Text(
                    text = "Login to your account",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    )
                )

                Spacer(modifier = Modifier.height(40.dp))

                // Formulario
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Email Input
                    EmailOutlinedField(
                        placeholder = "Enter Your Email",
                        label = "Email",
                        modifier = Modifier.fillMaxWidth(),
                        placeholderTextStyle = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Gray
                        ),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_usuario),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        value = email.value,
                        onValueChange = { email.value = it }
                    )

                    // Password Input
                    PasswordOutlinedField(
                        placeholder = "Enter Your Password",
                        label = "Password",
                        modifier = Modifier.fillMaxWidth(),
                        placeholderTextStyle = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Gray
                        ),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_candado),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        value = password.value,
                        onValueChange = { password.value = it }
                    )

                    // Forgot Password
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        LinkText(
                            text = "Forgot password?",
                            route = "welcomeScreen",
                            navController = navController,

                        )
                    }

                    // Login Button
                    PrimaryButton(
                        onClick = {
                            viewModel.loginUser(
                                LoginCredentialsModel(
                                    email = email.value,
                                    password = password.value
                                )
                            )
                        },
                        text = "Login",
                        isNavigationArrowVisible = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Brown
                        ),
                        cornerRadius = 12.dp
                    )

                    // Error Message
                    when (loginState) {
                        "Login exitoso" -> {
                            LaunchedEffect(Unit) {
                                viewModel.resetState()
                                onLoginSuccess()
                            }
                        }
                        null -> {} // No mostrar nada
                        else -> {
                            Text(
                                text = loginState ?: "",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Social Login
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Or login with",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        SocialIconButton(
                            iconResId = R.drawable.ic_apple,
                            modifier = Modifier.size(48.dp)
                        )
                        SocialIconButton(
                            iconResId = R.drawable.ic_google,
                            modifier = Modifier.size(48.dp)
                        )
                        SocialIconButton(
                            iconResId = R.drawable.ic_facebook,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // Register Link
                Row(
                    modifier = Modifier.padding(bottom = 32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Don't have an account? ",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                        )
                    )
                    LinkText(
                        text = "Register",
                        route = "RegisterScreen",
                        navController = navController,

                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController()
    LoginScreen(
        navController = navController,
        onLoginSuccess = {}
    )
}