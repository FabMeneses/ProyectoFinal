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
){

    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val registrationState = viewModel.registrationState
    val rememberMeChecked = remember { mutableStateOf(false) }

    Box (modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background))

    {
        // COLUMN PRINCIPAL
        Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)

    {
        Spacer(modifier = Modifier.height(75.dp))

        // ENCABEZADO //
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Create Account",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 24.dp),
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Fill your information below or register with you social account",
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
            placeholder = "Enter Your Name",
            label = " Name",
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .width(350.dp)
                .height(60.dp),
            placeholderTextStyle = TextStyle(
                fontSize = 14.sp,  // Tamaño personalizado
                color = Color.Gray  // Color personalizado
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
            onValueChange = {name.value = it}
    )

        Spacer(modifier = Modifier.height(24.dp))
        //INPUT EMAIL //
        EmailOutlinedField(
            placeholder = "Enter Your Email",
            label = "Email",
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .width(350.dp)
                .height(60.dp),
            placeholderTextStyle = TextStyle(
                fontSize = 14.sp,  // Tamaño personalizado
                color = Color.Gray  // Color personalizado
            ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_sobre),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(20.dp)
            )
        },   value = email.value,
            onValueChange = {email.value = it}

        )

        Spacer(modifier = Modifier.height(24.dp))

        //IMPUT CONTRASEÑA
        PasswordOutlinedField(
            placeholder = "Enter Your Password",
            label = "Password",
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .width(350.dp)
                .height(60.dp),
            placeholderTextStyle = TextStyle(
                fontSize = 16.sp,  // Tamaño personalizado
                color = Color.Gray  // Color personalizado
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_candado),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(24.dp)
                )
            },       value = password.value,
            onValueChange = { password.value = it },
        )

        Spacer(modifier = Modifier.height(12.dp))


        Row (
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .width(300.dp),
            //horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = rememberMeChecked.value,
                onCheckedChange = { rememberMeChecked.value = it },
                modifier = Modifier.size(24.dp) // Tamaño uniforme para el checkbox
            )
            Text(
                fontSize = 12.sp,
                text = " Agree With  ",
                color = MaterialTheme.colorScheme.onBackground)
            LinkText(
                text = "Terms & Conditions",
                route = "LoginScreen",
                navController = navController
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Botón de inicio de sesión
        PrimaryButton(
            onClick = {
                //onRegisterSuccess()
                viewModel.registerUser(
                    UserModel(
                        name = name.value,
                        email = email.value,
                        password = password.value
                    )
                )
            },
            text = "Register",
            isNavigationArrowVisible = true,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .width(250.dp)
                .height(45.dp)
            .shadow(
                elevation = 8.dp
        ),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White, // Color del texto del botón
                containerColor = Brown // Color de fondo del botón
            ),
            cornerRadius = 32.dp // Bordes redondeados
        )
        when (registrationState) {
            "Registro exitoso" -> {
                // Ejecuta la acción de éxito y resetea estado
                LaunchedEffect(Unit) {
                    viewModel.resetState()
                    onRegisterSuccess()
                }
            }
            null -> {} // No mostrar nada
            else -> {
                Text(
                    text = registrationState ?: "",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }



        Spacer(modifier = Modifier.height(50.dp))

        OrDivider()

        Spacer(modifier = Modifier.height(50.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            SocialIconButton(iconResId = R.drawable.ic_apple) // Ícono de Facebook
            Spacer(modifier = Modifier.width(16.dp))
            SocialIconButton(iconResId = R.drawable.ic_google) // Ícono de Google
            Spacer(modifier = Modifier.width(16.dp))
            SocialIconButton(iconResId = R.drawable.ic_facebook) // Ícono de Twitter
        }
        Spacer(modifier = Modifier.height(50.dp))
        // Opción de registro
        Row {
            Text(text = "Do you have an account? ",
                color = MaterialTheme.colorScheme.onBackground)
            LinkText(
                text = "Login",
                route = "LoginScreen",
                navController = navController
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    // Para el preview, creamos un NavController mock
    val navController = rememberNavController()

    RegisterScreen(
        navController = navController,
        onRegisterSuccess = {} // Lambda vacía para el preview
    )
}