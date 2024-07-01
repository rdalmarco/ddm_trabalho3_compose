import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ddm_trabalho3.ui.components.BottomNavigationBar
import com.example.ddm_trabalho3.ui.viewmodels.Perfil.PerfilViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PerfilScreen(navController: NavController, viewModel: PerfilViewModel) {

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }


    LaunchedEffect(Unit) {
        viewModel.loadUser()
    }

    val userLoadedEvent by viewModel.userLoadedEvent.observeAsState()
    userLoadedEvent?.getContentIfNotHandled()?.let { user ->
        nome = user.nome
        email = user.email
        senha = user.senha
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .size(width = 150.dp, height = 130.dp)
                    .background(color = Color.LightGray)
                    .align(Alignment.CenterHorizontally)
            ) {
                // Implemente a lógica para exibir a imagem do perfil
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { /* Ação para capturar imagem */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                Text("Capturar Imagem")
            }

            TextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            TextField(
                value = email,
                onValueChange = { /* Não faz nada quando o texto muda, pois é somente leitura */ },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                enabled = false
            )

            TextField(
                value = senha,
                onValueChange = { senha = it },
                label = { Text("Senha") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Button(
                        onClick = { viewModel.salvarAlteracoes(nome, email, senha) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Salvar")
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = {
                            viewModel.excluirUsuario {
                                navController.navigate("homeScreen")
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Excluir")
                    }
                }
            )
        }
    }
}
