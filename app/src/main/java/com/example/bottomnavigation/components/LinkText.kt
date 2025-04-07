package com.example.bottomnavigation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bottomnavigation.ui.theme.Brown

@Composable
fun LinkText(
    text: String,
    route: String,
    navController: NavController?,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Text(
        text = text,
        color = if (isPressed) Brown.copy(alpha = 0.7f) else Brown,
        fontSize = 12.sp,
        textDecoration = TextDecoration.Underline,
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { navController?.navigate(route) }
            )
    )
}