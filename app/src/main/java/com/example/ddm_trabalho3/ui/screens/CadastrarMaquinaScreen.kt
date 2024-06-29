import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ddm_trabalho3.R
import com.example.ddm_trabalho3.ui.components.BottomNavigationBar
import com.example.ddm_trabalho3.ui.viewmodels.Maquina.MaquinaViewModel
import com.example.ddm_trabalho3.ui.viewmodels.Maquina.MaquinaViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CadastrarMaquinaScreen(navController: NavController) {
    var tag by remember { mutableStateOf("") }
    var modelo by remember { mutableStateOf("") }
    var observacao by remember { mutableStateOf("") }
    var status by remember { mutableStateOf(true) }

    val context = LocalContext.current
    val maquinaViewModel: MaquinaViewModel = viewModel(factory = MaquinaViewModelFactory(context))

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 50.dp)
        ) {
            BasicTextField(
                value = tag,
                onValueChange = { tag = it },
                textStyle = TextStyle.Default.copy(color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(bottom = 16.dp)
                    .background(Color.LightGray),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                decorationBox = { innerTextField ->
                    Box(
                        Modifier
                            .padding(8.dp)
                    ) {
                        if (tag.isEmpty()) {
                            Text("Código", color = Color.Gray)
                        }
                        innerTextField()
                    }
                }
            )

            BasicTextField(
                value = modelo,
                onValueChange = { modelo = it },
                textStyle = TextStyle.Default.copy(color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(bottom = 16.dp)
                    .background(Color.LightGray),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                decorationBox = { innerTextField ->
                    Box(
                        Modifier
                            .padding(8.dp)
                    ) {
                        if (modelo.isEmpty()) {
                            Text("Modelo", color = Color.Gray)
                        }
                        innerTextField()
                    }
                }
            )

            BasicTextField(
                value = observacao,
                onValueChange = { observacao = it },
                textStyle = TextStyle.Default.copy(color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(bottom = 16.dp)
                    .background(Color.LightGray),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                decorationBox = { innerTextField ->
                    Box(
                        Modifier
                            .padding(8.dp)
                    ) {
                        if (observacao.isEmpty()) {
                            Text("Observação", color = Color.Gray)
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        maquinaViewModel.cadastrarMaquina(tag, modelo, observacao, status)
                        navController.navigate("consultaMaquinasScreen")      },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Salvar")
                }

                Button(
                    onClick = { /* Ação para excluir */ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Excluir")
                }
            }
        }
    }
}
