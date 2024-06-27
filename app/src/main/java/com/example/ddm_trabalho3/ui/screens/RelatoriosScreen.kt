import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavController
import com.example.ddm_trabalho3.ui.components.BottomNavigationBar
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RelatoriosScreen(navController: NavController) {
    var tipoSelecionado by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var dataInicio by remember { mutableStateOf("") }
    var dataFim by remember { mutableStateOf("") }
    val relatorioList = listOf("Relatório 1", "Relatório 2", "Relatório 3") // Exemplo de dados

    val context = LocalContext.current

    // DatePickerDialog para data de início
    val calendarInicio = Calendar.getInstance()
    val datePickerDialogInicio = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            dataInicio = "$dayOfMonth/${month + 1}/$year"
        }, calendarInicio.get(Calendar.YEAR), calendarInicio.get(Calendar.MONTH), calendarInicio.get(Calendar.DAY_OF_MONTH)
    )

    // DatePickerDialog para data de fim
    val calendarFim = Calendar.getInstance()
    val datePickerDialogFim = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            dataFim = "$dayOfMonth/${month + 1}/$year"
        }, calendarFim.get(Calendar.YEAR), calendarFim.get(Calendar.MONTH), calendarFim.get(Calendar.DAY_OF_MONTH)
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 50.dp, bottom = 16.dp)
        ) {
            // Spinner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(Color.LightGray)
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = if (tipoSelecionado.isEmpty()) "Selecione o tipo" else tipoSelecionado,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.White)
                        .clickable { expanded = true }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    val tipos = listOf("Tipo 1", "Tipo 2", "Tipo 3")
                    tipos.forEach { tipo ->
                        DropdownMenuItem(onClick = {
                            tipoSelecionado = tipo
                            expanded = false
                        }) {
                            Text(text = tipo)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            // Campo de entrada para data de início
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.LightGray)
                    .clickable { datePickerDialogInicio.show() }
                    .padding(8.dp)
            ) {
                Text(
                    text = if (dataInicio.isEmpty()) "Data de Início" else dataInicio,
                    color = if (dataInicio.isEmpty()) Color.Gray else Color.Black
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Campo de entrada para data de fim
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.LightGray)
                    .clickable { datePickerDialogFim.show() }
                    .padding(8.dp)
            ) {
                Text(
                    text = if (dataFim.isEmpty()) "Data de Fim" else dataFim,
                    color = if (dataFim.isEmpty()) Color.Gray else Color.Black
                )
            }

            Spacer(modifier = Modifier.height(60.dp))

            // Botão Gerar Relatório
            Button(
                onClick = { /* Lógica para gerar relatório */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Gerar Relatório")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // RecyclerView
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp)
            ) {
                items(relatorioList) { relatorio ->
                    Text(
                        text = relatorio,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    )
                }
            }
        }
    }
}
