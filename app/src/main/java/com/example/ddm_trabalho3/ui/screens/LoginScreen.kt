package com.example.ddm_trabalho3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ddm_trabalho3.ui.components.BottomNavigationBar

@Composable
fun LoginScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = { /* Implementar ação do botão Voltar */ },
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.Start),
            ) {
                Text(text = "Voltar")
            }

            OutlinedTextField(
                value = "",
                onValueChange = { /* Implementar lógica de alteração do texto */ },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = "",
                onValueChange = { /* Implementar lógica de alteração do texto */ },
                label = { Text("Senha") },
                modifier = Modifier.fillMaxWidth(),
            )

            Button(
                onClick = { navController.navigate("inicialScreen")  },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Entrar")
            }

            Button(
                onClick = { navController.navigate("cadastrarScreen") },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Cadastrar")
            }
        }
    }
}
