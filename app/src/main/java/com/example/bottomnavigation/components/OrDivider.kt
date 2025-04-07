package com.example.bottomnavigation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OrDivider() {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Divider(
//            modifier = Modifier.weight(1f),
//            color = Color.Gray.copy(alpha = 0.4f),
//            thickness = 1.dp
//        )
//
//        Text(
//            text = "Or sign in with",
//            style = TextStyle(
//                fontSize = 14.sp,
//                color = Color.Gray,
//                fontWeight = FontWeight.Medium
//            ),
//            modifier = Modifier.padding(horizontal = 12.dp)
//        )
//
//        Divider(
//            modifier = Modifier.weight(1f),
//            color = Color.Gray.copy(alpha = 0.4f),
//            thickness = 1.dp
//        )
//    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "──────────",
            color = Color.Gray.copy(alpha = 0.4f),
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Text(
            text = "Or sign in with",
            style = TextStyle(
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Medium
            )
        )

        Text(
            text = "──────────",
            color = Color.Gray.copy(alpha = 0.4f),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}