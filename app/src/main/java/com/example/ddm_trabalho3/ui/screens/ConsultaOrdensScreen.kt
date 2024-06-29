import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ddm_trabalho3.ui.components.BottomNavigationBar
import com.example.ddm_trabalho3.ui.viewmodels.Maquina.MaquinaViewModel
import com.example.ddm_trabalho3.ui.viewmodels.Maquina.MaquinaViewModelFactory
import com.example.ddm_trabalho3.ui.viewmodels.Ordem.OrdemViewModel
import com.example.ddm_trabalho3.ui.viewmodels.Ordem.OrdemViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ConsultaOrdensScreen(navController: NavController) {
    var filtroText by remember { mutableStateOf("") }

    val context = LocalContext.current
    val ordemViewModel: OrdemViewModel = viewModel(factory = OrdemViewModelFactory(context))
    val ordemList by ordemViewModel.listaOrdens.collectAsState()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Botão Cadastrar
            Button(
                onClick = { navController.navigate("cadastrarOrdemScreen") },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Cadastrar")
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

            Spacer(modifier = Modifier.height(8.dp))

            // ListView
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 16.dp)
            ) {
                items(ordemList) { ordem ->
                    Text(
                        text =  "${"Tag: " + ordem.tag} \n" +
                                "${"Tipo: " + ordem.tipo} \n" +
                                "${"Descrição: " + ordem.descriao} \n" +
                                "${"Data Criação: " + ordem.dataCriacao}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    )
                }
            }
        }
    }
}