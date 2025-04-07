package com.example.bottomnavigation.screen

import androidx.compose.foundation.background
import androidx.compose.material3.Icon
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigation.R
import com.example.bottomnavigation.components.EmailOutlinedField
import com.example.bottomnavigation.components.LinkText
import com.example.bottomnavigation.components.NameOutlinedField
import com.example.bottomnavigation.components.OrDivider
import com.example.bottomnavigation.components.PasswordOutlinedField
import com.example.bottomnavigation.components.PrimaryButton
import com.example.bottomnavigation.components.SocialIconButton
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
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

            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .width(300.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = rememberMeChecked.value,
                    onCheckedChange = { rememberMeChecked.value = it },
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    fontSize = 12.sp,
                    text = "Acepto los ",
                    color = MaterialTheme.colorScheme.onBackground
                )
                LinkText(
                    text = "Términos y condiciones",
                    route = "LoginScreen",
                    navController = navController
                )
            }

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
