package com.example.ddm_trabalho3.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ddm_trabalho3.R
import com.example.ddm_trabalho3.ui.viewmodels.Login.LoginViewModel
import com.example.ddm_trabalho3.ui.viewmodels.Login.LoginViewModelFactory
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(context))

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    val showToastEvent by loginViewModel.showToastEvent.observeAsState()
    val userLoggedInEvent by loginViewModel.userLoggedInEvent.observeAsState()

    LaunchedEffect(userLoggedInEvent) {
        userLoggedInEvent?.getContentIfNotHandled()?.let { loggedIn ->
            if (loggedIn) {
                showToastEvent?.getContentIfNotHandled()?.let { message ->
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    delay(2000)
                    navController.navigate("inicialScreen")
                }
            }
        }
    }

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
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.Start)
                    .padding(bottom = 110.dp),
            ) {
                Text(text = "Voltar")
            }

            Image(
                painter = painterResource(id = R.drawable.logosmartain),
                contentDescription = "Imagem",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 16.dp),
                contentScale = ContentScale.Fit
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .padding(top = 8.dp),
                label = { Text(text = "Email") },
                singleLine = true,
            )

            TextField(
                value = senha,
                onValueChange = { senha = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .padding(top = 8.dp),
                label = { Text(text = "Senha") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )

            Button(
                onClick = { loginViewModel.login(email, senha) },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Entrar")
            }
        }
    }
}