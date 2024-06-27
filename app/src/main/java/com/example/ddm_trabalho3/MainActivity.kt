package com.example.ddm_trabalho3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ddm_trabalho3.ui.screens.CadastrarScreen
import com.example.ddm_trabalho3.ui.screens.HomeScreen
import com.example.ddm_trabalho3.ui.screens.InicialScreen
import com.example.ddm_trabalho3.ui.screens.LoginScreen
import com.example.ddm_trabalho3.ui.theme.Ddm_trabalho3Theme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ddm_trabalho3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // Initialize navigation controller
                    val navController = rememberNavController()

                    // NavHost for navigation destinations
                    NavHost(navController  = navController, startDestination = "homeScreen") {
                        composable("homeScreen") { HomeScreen(navController) }
                        composable("loginScreen") { LoginScreen(navController) }
                        composable("inicialScreen") { InicialScreen(navController) }
                        composable("cadastrarScreen") { CadastrarScreen(navController) }
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