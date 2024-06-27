// BottomNavigationBar.kt

package com.example.ddm_trabalho3.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(modifier = androidx.compose.ui.Modifier.fillMaxWidth()) {
        BottomNavigationItem(
            selected = navController.currentDestination?.route == "inicialScreen",
            onClick = { navController.navigate("inicialScreen") },
            icon = { /* Icon */ },
            label = { androidx.compose.material3.Text("Inicial Screen") }
        )

        BottomNavigationItem(
            selected = navController.currentDestination?.route == "perfilScreen",
            onClick = { navController.navigate("perfilScreen") },
            icon = { /* Icon */ },
            label = { androidx.compose.material3.Text("Perfil Screen") }
        )
    }
}
