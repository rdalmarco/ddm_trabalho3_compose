package com.example.ddm_trabalho3.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ddm_trabalho3.ui.components.BottomNavigationBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(

    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = com.example.ddm_trabalho3.R.drawable.logosmartain),
                    contentDescription = "Imagem",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(bottom = 16.dp),
                    contentScale = androidx.compose.ui.layout.ContentScale.Fit
                )

                Button(
                    onClick = { navController.navigate("loginScreen")  },
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .width(200.dp)
                ) {
                    Text("Entrar")
                }

                Button(
                    onClick = { navController.navigate("cadastrarScreen") },
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .width(200.dp),
                ) {
                    Text("Cadastrar-se")
                }
            }
        }
    }
}
