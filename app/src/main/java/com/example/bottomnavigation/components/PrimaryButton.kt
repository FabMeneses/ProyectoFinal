package com.example.bottomnavigation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    isNavigationArrowVisible: Boolean,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shadowColor: Color = Color.Gray,
    navigationIcon: ImageVector? = null,
    cornerRadius: Dp =4.dp
) {
    val shape = RoundedCornerShape(cornerRadius)

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(62.dp)
            .shadow(
                elevation = 8.dp,
                shape = shape,
                spotColor = shadowColor
            )
            .clip(shape),
        colors = colors,
        shape = shape
    ) {
        if (isNavigationArrowVisible && navigationIcon != null) {
            Icon(
                imageVector = navigationIcon,
                contentDescription = "Navigation Arrow",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }
}


@Preview
@Composable
fun Preview_PrimaryButton(){
    PrimaryButton(
        onClick = {},
        text = "string",
        isNavigationArrowVisible = false,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color.DarkGray
        ), modifier = Modifier
            .shadow(16.dp),
        shadowColor = Color.Blue,
        cornerRadius = 32.dp
    )
}