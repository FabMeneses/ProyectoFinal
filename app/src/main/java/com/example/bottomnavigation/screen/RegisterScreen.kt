package com.example.bottomnavigation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bottomnavigation.R
import com.example.bottomnavigation.components.*
import com.example.bottomnavigation.models.UserModel
import com.example.bottomnavigation.ui.theme.Brown
import com.example.bottomnavigation.viewmodels.RegisterViewModel

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = viewModel(),
    onRegisterSuccess: () -> Unit
) {
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val registrationState = viewModel.registrationState
    val rememberMeChecked = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 32.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(75.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Crear Cuenta",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 24.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Llena tus datos abajo",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))

                NameOutlinedField(
                    placeholder = "Ingresa tu nombre",
                    label = " Nombre",
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .width(350.dp)
                        .height(60.dp),
                    placeholderTextStyle = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Gray
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_usuario),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    value = name.value,
                    onValueChange = { name.value = it }
                )

                Spacer(modifier = Modifier.height(24.dp))

                EmailOutlinedField(
                    placeholder = "Ingresa tu correo electrónico",
                    label = "Correo electrónico",
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .width(350.dp)
                        .height(60.dp),
                    placeholderTextStyle = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Gray
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sobre),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    value = email.value,
                    onValueChange = { email.value = it }
                )

                Spacer(modifier = Modifier.height(24.dp))

                PasswordOutlinedField(
                    placeholder = "Ingresa tu contraseña",
                    label = "Contraseña",
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .width(350.dp)
                        .height(60.dp),
                    placeholderTextStyle = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Gray
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_candado),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    value = password.value,
                    onValueChange = { password.value = it }
                )

                Spacer(modifier = Modifier.height(12.dp))


                Spacer(modifier = Modifier.height(24.dp))

                PrimaryButton(
                    onClick = {
                        viewModel.registerUser(
                            UserModel(
                                name = name.value,
                                email = email.value,
                                password = password.value
                            )
                        )
                    },
                    text = "Registrarse",
                    isNavigationArrowVisible = true,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .width(250.dp)
                        .height(45.dp)
                        .shadow(elevation = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    cornerRadius = 32.dp
                )

                when (registrationState) {
                    "Registro exitoso" -> {
                        LaunchedEffect(Unit) {
                            viewModel.resetState()
                            onRegisterSuccess()
                        }
                    }
                    null -> {}
                    else -> {
                        Text(
                            text = registrationState ?: "",
                            color = Color.Red,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(50.dp))

                Row {
                    Text(
                        text = "¿Ya tienes una cuenta? ",
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    LinkText(
                        text = "Iniciar sesión",
                        route = "LoginScreen",
                        navController = navController
                    )
                }
            }
        }
    }
}
