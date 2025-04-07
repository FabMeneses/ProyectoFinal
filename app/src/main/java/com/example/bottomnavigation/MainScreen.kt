package com.example.bottomnavigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bottomnavigation.pages.*
import androidx.compose.foundation.shape.CircleShape
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun CustomFloatingActionButton(
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier.size(64.dp),
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        shape = CircleShape
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier,
        navController: NavHostController
) {
    val navItemList = listOf(
        NavItem("Home", Icons.Default.Home, 0),
        NavItem("Notification", Icons.Default.Notifications, 5),
        NavItem("Settings", Icons.Default.Settings, 0),
        NavItem("Profile", Icons.Default.AccountCircle, 0)
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Box(contentAlignment = Alignment.TopCenter) {
                NavigationBar {
                    navItemList.take(2).forEachIndexed { index, navItem ->
                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = { selectedIndex = index },
                            icon = {
                                BadgedBox(badge = {
                                    if (navItem.badgeCount > 0) {
                                        Badge { Text(text = navItem.badgeCount.toString()) }
                                    }
                                }) {
                                    Icon(navItem.icon, contentDescription = "Icon")
                                }
                            },
                            label = { Text(text = navItem.label) }
                        )
                    }

                    Spacer(modifier = Modifier.width(72.dp))

                    navItemList.drop(2).forEachIndexed { index, navItem ->
                        NavigationBarItem(
                            selected = selectedIndex == index + 2,
                            onClick = { selectedIndex = index + 2 },
                            icon = { Icon(navItem.icon, contentDescription = "Icon") },
                            label = { Text(text = navItem.label) }
                        )
                    }
                }

                CustomFloatingActionButton(
                    onClick = { /* Acción de prueba */ },
                    icon = Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier.offset(y = (-28).dp)
                )
            }
        }
    ) { innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding), selectedIndex)
    }
}

@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex: Int) {
    when (selectedIndex) {
        0 -> HomePage()
        1 -> NotificationPage()
        2 -> SettingsPage()
        3 -> ProfilePage()
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MainScreen(navController = rememberNavController())
}