import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ddm_trabalho3.R
import com.example.ddm_trabalho3.ui.components.BottomNavigationBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CadastrarMaquinaScreen(navController: NavController) {
    var tagText by remember { mutableStateOf("") }
    var modeloText by remember { mutableStateOf("") }
    var observacaoText by remember { mutableStateOf("") }

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
                value = tagText,
                onValueChange = { tagText = it },
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
                        if (tagText.isEmpty()) {
                            Text("Código", color = Color.Gray)
                        }
                        innerTextField()
                    }
                }
            )

            BasicTextField(
                value = modeloText,
                onValueChange = { modeloText = it },
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
                        if (modeloText.isEmpty()) {
                            Text("Modelo", color = Color.Gray)
                        }
                        innerTextField()
                    }
                }
            )

            BasicTextField(
                value = observacaoText,
                onValueChange = { observacaoText = it },
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
                        if (observacaoText.isEmpty()) {
                            Text("Observação", color = Color.Gray)
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botões Cancelar e Confirmar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /* Ação para salvar */ },
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
