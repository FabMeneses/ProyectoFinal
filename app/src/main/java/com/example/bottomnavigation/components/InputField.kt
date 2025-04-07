package com.example.bottomnavigation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bottomnavigation.R
import com.example.bottomnavigation.ui.theme.DarkTextColor
import androidx.compose.ui.unit.sp

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    placeholder: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,  // Nuevo parámetro para el ícono a la derecha
    textStyle: TextStyle = TextStyle(fontSize = 16.sp)
) {
    var inputValue by remember { mutableStateOf("") }

    TextField(
        value = inputValue,
        onValueChange = { inputValue = it },
        placeholder = { Text(text = placeholder) },
        visualTransformation = visualTransformation,
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(56.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedTextColor = DarkTextColor,
            unfocusedTextColor = DarkTextColor,
            unfocusedPlaceholderColor = DarkTextColor,
            focusedPlaceholderColor = DarkTextColor,
            focusedLeadingIconColor = DarkTextColor,
            unfocusedLeadingIconColor = DarkTextColor,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        textStyle = textStyle,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon  // Añadido el parámetro trailingIcon
    )
}


@Preview(showBackground = true)
@Composable
fun InputFieldPreview(){
    InputField(
        placeholder = "Enter text",
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_bola_amarilla),
                contentDescription = null,
                tint = DarkTextColor,
                modifier = Modifier.size(24.dp)
            )
        },
        visualTransformation = VisualTransformation.None
    )
}