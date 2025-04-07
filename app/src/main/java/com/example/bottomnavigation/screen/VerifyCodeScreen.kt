package com.example.bottomnavigation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigation.R
import com.example.bottomnavigation.components.PrimaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyCodeScreen(
    navController: NavHostController,
    onVerifyCodeSuccess: () -> Unit
) {
    var code by remember { mutableStateOf("") }

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
                // Botón de retroceso
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                )

                Spacer(modifier = Modifier.height(64.dp))

                // Encabezado
                Text(
                    text = "Verificar código",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Por favor, ingresa el código que acabamos de enviar a tu correo electrónico",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "ejemplo@gmail.com",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(48.dp))

                // Código de verificación (4 dígitos)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(4) { index ->
                        TextField(
                            value = code.getOrNull(index)?.toString() ?: "",
                            onValueChange = {
                                if (it.length <= 1) {
                                    code = code.take(index) + it + code.drop(index + 1)
                                }
                            },
                            modifier = Modifier
                                .width(64.dp)
                                .height(56.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            singleLine = true,
                            textStyle = LocalTextStyle.current.copy(
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(48.dp))

                // Enlace "Reenviar código"
                Text(
                    text = "¿No recibiste el OTP?",
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.clickable { /* Lógica para reenviar código */ },
                    fontSize = 14.sp
                )

                Text(
                    text = "Reenviar código",
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { /* Lógica para reenviar código */ },
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(32.dp))

                PrimaryButton(
                    onClick = {
                        onVerifyCodeSuccess()
                    },
                    text = "Verificar",
                    isNavigationArrowVisible = true,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .width(250.dp)
                        .height(45.dp)
                        .shadow(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    cornerRadius = 32.dp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerifyCodeScreenPreview() {
    val navController = rememberNavController()
    VerifyCodeScreen(
        navController = navController,
        onVerifyCodeSuccess = {}
    )
}
