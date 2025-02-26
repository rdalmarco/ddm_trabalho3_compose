import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun CadastrarOrdemScreen(navController: NavController) {
    var descricaoText by remember { mutableStateOf("") }
    var maqCodigoSelectedItem by remember { mutableStateOf("") }
    var statusSelectedItem by remember { mutableStateOf("") }
    var tipoSelectedItem by remember { mutableStateOf("") }

    // Estados para controle da expansão dos DropdownMenus
    var maqCodigoExpanded by remember { mutableStateOf(false) }
    var statusExpanded by remember { mutableStateOf(false) }
    var tipoExpanded by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val ordemViewModel: OrdemViewModel = viewModel(factory = OrdemViewModelFactory(context))
    val listaMaquinas by ordemViewModel.listaMaquinas.collectAsState()

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
                value = descricaoText,
                onValueChange = { descricaoText = it },
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
                        if (descricaoText.isEmpty()) {
                            Text("Descrição", color = Color.Black)
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Spinner para código de máquina (simulado)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.LightGray)
                    .padding(vertical = 8.dp)
                    .clickable {
                        // Abrir ou fechar o DropdownMenu
                        maqCodigoExpanded = !maqCodigoExpanded
                    }
            ) {
                Text(
                    text = if (maqCodigoSelectedItem.isEmpty()) "Selecione o código de máquina" else maqCodigoSelectedItem,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.LightGray)
                        .clickable {
                            maqCodigoExpanded = !maqCodigoExpanded
                        }
                )
                DropdownMenu(
                    expanded = maqCodigoExpanded,
                    onDismissRequest = { maqCodigoExpanded = false }
                ) {
                    listaMaquinas.forEach { maquina ->
                        DropdownMenuItem(onClick = {
                            maqCodigoSelectedItem = maquina.tag
                            maqCodigoExpanded = false
                        }) {
                            Text(text = maquina.tag)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Spinner para status (simulado)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.LightGray)
                    .padding(vertical = 8.dp)
                    .clickable {
                        // Abrir ou fechar o DropdownMenu
                        statusExpanded = !statusExpanded
                    }
            ) {
                Text(
                    text = if (statusSelectedItem.isEmpty()) "Selecione o status" else statusSelectedItem,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.LightGray)
                        .clickable {
                            statusExpanded = !statusExpanded
                        }
                )
                DropdownMenu(
                    expanded = statusExpanded,
                    onDismissRequest = { statusExpanded = false }
                ) {
                    val statusList = listOf("Status 1", "Status 2", "Status 3")
                    statusList.forEach { status ->
                        DropdownMenuItem(onClick = {
                            statusSelectedItem = status
                            statusExpanded = false
                        }) {
                            Text(text = status)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Spinner para tipo (simulado)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.LightGray)
                    .padding(vertical = 8.dp)
                    .clickable {
                        // Abrir ou fechar o DropdownMenu
                        tipoExpanded = !tipoExpanded
                    }
            ) {
                Text(
                    text = if (tipoSelectedItem.isEmpty()) "Selecione o tipo" else tipoSelectedItem,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.LightGray)
                        .clickable {
                            tipoExpanded = !tipoExpanded
                        }
                )
                DropdownMenu(
                    expanded = tipoExpanded,
                    onDismissRequest = { tipoExpanded = false }
                ) {
                    val tipos = listOf("Tipo 1", "Tipo 2", "Tipo 3")
                    tipos.forEach { tipo ->
                        DropdownMenuItem(onClick = {
                            tipoSelectedItem = tipo
                            tipoExpanded = false
                        }) {
                            Text(text = tipo)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        ordemViewModel.cadastrarOrdem(descricaoText, maqCodigoSelectedItem, true, tipoSelectedItem)
                        navController.navigate("consultaOrdensScreen")},
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
