package com.example.bottomnavigation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bottomnavigation.R
import com.example.bottomnavigation.ui.theme.DarkTextColor
import androidx.compose.ui.unit.sp

@Composable
fun EmailOutlinedField(
    modifier: Modifier = Modifier,
    placeholder: String,
    label: String = "",
    placeholderTextStyle: TextStyle = TextStyle(
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.onBackground// Color con transparencia
    ),
    labelTextStyle: TextStyle = TextStyle(  // <- Estilo fijo para el label
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.onBackground
    ),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable (() -> Unit)? = { // Icono por defecto
        Icon(
            painter = painterResource(id = R.drawable.ic_bola_amarilla),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.size(24.dp)
        )
    },
    trailingIcon: @Composable (() -> Unit)? = null,
    textStyle: TextStyle = TextStyle(fontSize = 16.sp),
    value: String,  // Recibe el valor externo
    onValueChange: (String) -> Unit  // Función para cambiar el valor
) {
    var inputValue by remember { mutableStateOf("") }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,  // Cambia el valor al que se pasa desde el Composable principal
        label = {
            Text(
                text = label,
                style = labelTextStyle
                )
        },
        placeholder = { Text(text = placeholder,
            style = placeholderTextStyle ) },
        visualTransformation = visualTransformation,
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(56.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onSurface,  // Texto en modo claro/oscuro
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
        trailingIcon = trailingIcon
    )
}

@Preview(showBackground = true)
@Composable
fun CustomOutlinedTextFieldPreview(){
    EmailOutlinedField(
        label = "Enter text",
        placeholder = "Enter text",
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_bola_amarilla),
                contentDescription = null,
                tint = DarkTextColor,
                modifier = Modifier.size(24.dp)
            )
        },
        visualTransformation = VisualTransformation.None,
        value = "John Doe", // Proporciona un valor estático
        onValueChange = {} // Deja la función vacía ya que no necesitas que haga nada en el preview
    )
}