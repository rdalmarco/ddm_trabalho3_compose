package com.example.ddm_trabalho3.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ddm_trabalho3.R
import com.example.ddm_trabalho3.ui.components.BottomNavigationBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InicialScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(32.dp))

                Image(
                    painter = painterResource(id = R.drawable.rio_riosulense),
                    contentDescription = "Logo da Empresa",
                    modifier = Modifier
                        .size(250.dp)
                        .clip(RoundedCornerShape(4.dp)),
                )

                Button(
                    onClick = { navController.navigate("consultaMaquinasScreen") },
                    modifier = Modifier
                        .width(250.dp)
                        .height(60.dp),
                ) {
                    Text(text = "Máquinas")
                }

                Button(
                    onClick = { navController.navigate("consultaOrdensScreen") },
                    modifier = Modifier
                        .width(250.dp)
                        .height(60.dp),
                ) {
                    Text(text = "Ordens de Serviço")
                }

                Button(
                    onClick = { navController.navigate("relatoriosScreen") },
                    modifier = Modifier
                        .width(250.dp)
                        .height(60.dp),
                ) {
                    Text(text = "Relatórios")
                }
            }
        }
    }
}
