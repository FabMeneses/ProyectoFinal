package com.example.bottomnavigation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bottomnavigation.R
import com.example.bottomnavigation.ui.theme.DarkTextColor
import androidx.compose.ui.unit.sp

@Composable
fun PasswordOutlinedField(
    modifier: Modifier = Modifier,
    placeholder: String,
    label: String = "",
    placeholderTextStyle: TextStyle = TextStyle(
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.onBackground // Color con transparencia
    ),
    leadingIcon: @Composable (() -> Unit)? = {
        // Icono por defecto
        Icon(
            painter = painterResource(id = R.drawable.huella),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.size(24.dp)
        )
    },
    textStyle: TextStyle = TextStyle.Default.copy(fontSize = 16.sp),
    value: String,  // Recibe el valor desde el composable principal
    onValueChange: (String) -> Unit,  // Función para manejar el cambio de valor
    onTogglePasswordVisibility: () -> Unit = {}
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,  // Usar la función proporcionada para cambiar el valor
        label = {
            Text(
                text = label,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = if (value.isNotEmpty()) 12.sp else 14.sp
                )
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                style = placeholderTextStyle  // Estilo de texto del placeholder
            )
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None
        else PasswordVisualTransformation(),
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(56.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
        ),
        textStyle = textStyle,
        leadingIcon = leadingIcon,
        trailingIcon = {
            IconButton(
                onClick = {
                    passwordVisible = !passwordVisible
                    onTogglePasswordVisibility()
                },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(
                        if (passwordVisible) R.drawable.ic_eye_visible
                        else R.drawable.ic_eye_hidden
                    ),
                    contentDescription = if (passwordVisible) "Hide password"
                    else "Show password",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PasswordOutlinedFieldPreview() {
    var password by remember { mutableStateOf("Password123") }  // Estado para la contraseña

    PasswordOutlinedField(
        value = password,  // Pasar el valor al campo
        onValueChange = { password = it },  // Actualizar el valor cuando cambie
        placeholder = "Enter password",
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.huella),
                contentDescription = "Password",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(24.dp)
            )
        },
        onTogglePasswordVisibility = {}
    )
}
