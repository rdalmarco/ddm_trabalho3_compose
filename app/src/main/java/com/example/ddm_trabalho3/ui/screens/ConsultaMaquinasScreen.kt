import android.annotation.SuppressLint
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.ddm_trabalho3.ui.components.BottomNavigationBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ConsultaMaquinasScreen(navController: NavController) {
    var filtroText by remember { mutableStateOf("") }
    val maquinasList = listOf("Máquina 1", "Máquina 2", "Máquina 3") // Exemplo de dados
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
                Button(
                    onClick = { navController.navigate("cadastrarMaquinaScreen") },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Cadastrar")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Botão Filtrar
                Button(
                    onClick = { /* Lógica de filtro */ },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Filtrar")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Campo de entrada para filtrar
                BasicTextField(
                    value = filtroText,
                    onValueChange = { filtroText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    singleLine = true,
                    textStyle = TextStyle.Default.copy(color = Color.Black),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    decorationBox = { innerTextField ->
                        Box(
                            Modifier
                                .background(Color.LightGray)
                                .padding(8.dp)
                        ) {
                            if (filtroText.isEmpty()) {
                                Text("Filtrar", color = Color.Gray)
                            }
                            innerTextField()
                        }
                    }
                )

                // ListView
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 16.dp)
                ) {
                    items(maquinasList) { maquina ->
                        Text(
                            text = maquina,
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
