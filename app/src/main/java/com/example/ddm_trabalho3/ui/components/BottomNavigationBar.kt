// BottomNavigationBar.kt

package com.example.ddm_trabalho3.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material3.*
import androidx.compose.ui.res.painterResource
import com.example.ddm_trabalho3.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(modifier = androidx.compose.ui.Modifier.fillMaxWidth()) {
        BottomNavigationItem(
            selected = navController.currentDestination?.route == "inicialScreen",
            onClick = { navController.navigate("inicialScreen") },
            icon = { Icon(
                painter = painterResource(id = R.drawable.casa100),
                contentDescription = "Icone casa"
            ) },
            label = { androidx.compose.material3.Text("Início") }
        )

        BottomNavigationItem(
            selected = navController.currentDestination?.route == "perfilScreen",
            onClick = { navController.navigate("perfilScreen") },
            icon = { Icon(
                painter = painterResource(id = R.drawable.perfil100),
                contentDescription = "Icone perfil"
            ) },
            label = { androidx.compose.material3.Text("Perfil") }
        )
    }
}
