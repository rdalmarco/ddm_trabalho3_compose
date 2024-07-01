package com.example.ddm_trabalho3.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.ddm_trabalho3.ui.viewmodels.Perfil.PerfilViewModel
import com.example.ddm_trabalho3.ui.viewmodels.Perfil.PerfilViewModelFactory
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CadastrarScreen(navController: NavController) {
    val context = LocalContext.current
    val perfilViewModel: PerfilViewModel = viewModel(factory = PerfilViewModelFactory(context))

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var showMessage by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    if (showMessage) {
        LaunchedEffect(Unit) {
            scaffoldState.snackbarHostState.showSnackbar(message)
            delay(2000)
            if (message == "Usuário cadastrado com sucesso!") {
                navController.navigate("loginScreen")
            }
            showMessage = false
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        content = {
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
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.Start)
                            .padding(bottom = 60.dp),
                    ) {
                        Text(text = stringResource(id = R.string.btnVoltar))
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
                        value = nome,
                        onValueChange = { nome = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        label = { Text(text = stringResource(id = R.string.edtNomeCompleto)) },
                        singleLine = true,
                    )

                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .padding(top = 8.dp),
                        label = { Text(text = stringResource(id = R.string.edtEmail)) },
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
                        label = { Text(text = stringResource(id = R.string.edtSenha)) },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation()
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Button(
                            onClick = {
                                perfilViewModel.cadastrarUser(nome, email, senha) { success, msg ->
                                    showMessage = true
                                    message = msg
                                }
                            },
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
    )
}