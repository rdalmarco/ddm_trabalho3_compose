// BottomNavigationBar.kt

package com.example.ddm_trabalho3.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ddm_trabalho3.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colorScheme.background,
    ) {
        BottomNavigationItem(
            selected = navController.currentDestination?.route == "inicialScreen",
            onClick = { navController.navigate("inicialScreen") },
            modifier = Modifier.padding(16.dp),
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.casa100),
                    contentDescription = "Icone casa",
                    tint = if (navController.currentDestination?.route == "inicialScreen") {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onBackground
                    }
                )
            },
            label = {
                Text(
                    "Início",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = if (navController.currentDestination?.route == "inicialScreen") {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onBackground
                        }
                    )
                )
            }
        )

        BottomNavigationItem(
            selected = navController.currentDestination?.route == "perfilScreen",
            onClick = { navController.navigate("perfilScreen") },
            modifier = Modifier.padding(16.dp),
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.perfil100),
                    contentDescription = "Icone perfil",
                    tint = if (navController.currentDestination?.route == "perfilScreen") {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onBackground
                    }
                )
            },
            label = {
                Text(
                    "Perfil",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = if (navController.currentDestination?.route == "perfilScreen") {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onBackground
                        }
                    )
                )
            }
        )
    }
}
