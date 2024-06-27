package com.example.ddm_trabalho3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ddm_trabalho3.R
import com.example.ddm_trabalho3.ui.components.BottomNavigationBar

@Composable
fun CadastrarScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = { /* Implementar ação do botão Voltar */ },
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(bottom = 110.dp),
            ) {
                Text(text = stringResource(id = R.string.btnVoltar))
            }

            TextField(
                value = "",
                onValueChange = { /* Implementar lógica para alterar o valor do campo */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(4.dp)),
                label = { Text(text = stringResource(id = R.string.edtNomeCompleto)) },
                singleLine = true,
            )

            TextField(
                value = "",
                onValueChange = { /* Implementar lógica para alterar o valor do campo */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .padding(top = 8.dp),
                label = { Text(text = stringResource(id = R.string.edtEmail)) },
                singleLine = true,
            )

            TextField(
                value = "",
                onValueChange = { /* Implementar lógica para alterar o valor do campo */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .padding(top = 8.dp),
                label = { Text(text = stringResource(id = R.string.edtSenha)) },
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(60.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = { navController.navigate("loginScreen") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp),
                ) {
                    Text(text = stringResource(id = R.string.btnConfirmar))
                }
            }
        }
    }
}
