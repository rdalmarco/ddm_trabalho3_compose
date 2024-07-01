import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ddm_trabalho3.ui.components.BottomNavigationBar
import com.example.ddm_trabalho3.ui.viewmodels.Maquina.MaquinaViewModel
import com.example.ddm_trabalho3.ui.viewmodels.Maquina.MaquinaViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ConsultaMaquinasScreen(navController: NavController) {
    var filtroText by remember { mutableStateOf("") }

    val context = LocalContext.current
    val maquinaViewModel: MaquinaViewModel = viewModel(factory = MaquinaViewModelFactory(context))
    val maquinasList by maquinaViewModel.listaMaquinas.collectAsState()

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
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Botão Cadastrar
                androidx.compose.material3.Button(
                    onClick = { navController.navigate("cadastrarMaquinaScreen") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 8.dp),
                ) {
                    Text(
                        text = "Cadastrar",
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Campo de entrada para filtrar
                TextField(
                    value = filtroText,
                    onValueChange = { filtroText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    textStyle = TextStyle.Default.copy(color = if (filtroText.isEmpty()) Color.Gray else Color.Black),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    placeholder = {
                        Text("Digite para buscar...", color = Color.Gray)
                    }
                )

                // Botão Filtrar
                androidx.compose.material3.Button(
                    onClick = { /* Lógica de filtro */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 2.dp, bottom = 8.dp),
                ) {
                    Text(
                        text = "Filtrar",
                        color = Color.White
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 16.dp)
                ) {
                    items(maquinasList) { maquina ->

                        val text = buildAnnotatedString {
                            withStyle(style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp).toSpanStyle()) {
                                append("Tag: ${maquina.tag}\n")
                            }
                            append("Modelo: ${maquina.modelo}\n")
                            append("Observação: ${maquina.observacao}\n")
                            append("Status: ${maquina.status}")
                        }

                        Text(
                            text =  text,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                        )
                    }
                }
            }
        }
    }
}