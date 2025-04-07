package com.example.bottomnavigation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.bottomnavigation.ui.theme.Brown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyCodeScreen(
    navController: NavHostController,
    onVerifyCodeSuccess: () -> Unit) {
    var code by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Botón de retroceso
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .size(15.dp)
                        .align(Alignment.TopStart)
                        .background(Color.White, shape = CircleShape)
                        .border(1.dp, Color.Gray, CircleShape)
                        .shadow(4.dp, shape = CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_flecha_izq),
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(64.dp))

            // Encabezado
            Text(
                text = "Verify Code",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Please enter the code we just sent to email",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                textAlign = TextAlign.Center
            )

            Text(
                text = "example@gmail.com",
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

            // Enlace "Resend code"
            Text(
                text = "Didn't receive OTP?",
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.clickable { /* Resend Code Logic */ },
                fontSize = 14.sp
            )

            Text(
                text = "Resend code",
                textDecoration = TextDecoration.Underline,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { /* Resend Code Logic */ },
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            PrimaryButton(
                onClick = {
                    onVerifyCodeSuccess()
                },
                text = "Verify",
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerifyCodeScreenPreview(){
    val navController = rememberNavController()
    VerifyCodeScreen(
        navController = navController,
        onVerifyCodeSuccess = {}
    )
}
