package com.example.ddm_trabalho3

import CadastrarMaquinaScreen
import CadastrarOrdemScreen
import ConsultaMaquinasScreen
import ConsultaOrdensScreen
import PerfilScreen
import RelatoriosScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ddm_trabalho3.ui.screens.CadastrarScreen
import com.example.ddm_trabalho3.ui.screens.HomeScreen
import com.example.ddm_trabalho3.ui.screens.InicialScreen
import com.example.ddm_trabalho3.ui.screens.LoginScreen
import com.example.ddm_trabalho3.ui.theme.Ddm_trabalho3Theme
import com.example.ddm_trabalho3.ui.viewmodels.Perfil.PerfilViewModel
import com.example.ddm_trabalho3.ui.viewmodels.Perfil.PerfilViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ddm_trabalho3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = "homeScreen") {
                        composable("homeScreen") { HomeScreen(navController) }
                        composable("loginScreen") { LoginScreen(navController) }
                        composable("inicialScreen") { InicialScreen(navController) }
                        composable("cadastrarScreen") { CadastrarScreen(navController) }
                        composable("consultaMaquinasScreen") { ConsultaMaquinasScreen(navController) }
                        composable("consultaOrdensScreen") { ConsultaOrdensScreen(navController) }
                        composable("relatoriosScreen") { RelatoriosScreen(navController) }
                        composable("perfilScreen") {
                            val perfilViewModel: PerfilViewModel = viewModel(
                                factory = PerfilViewModelFactory(applicationContext)
                            )
                            PerfilScreen(navController, perfilViewModel)
                        }
                        composable("cadastrarMaquinaScreen") { CadastrarMaquinaScreen(navController) }
                        composable("cadastrarOrdemScreen") { CadastrarOrdemScreen(navController) }
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Ddm_trabalho3Theme {
        MainActivity()
    }
}