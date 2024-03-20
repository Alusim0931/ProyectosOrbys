package com.example.pruebamoviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pruebamoviedb.ui.screens.DetailsScreen
import com.example.pruebamoviedb.ui.screens.HomeScreen
import com.example.pruebamoviedb.util.Screen
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkTheme = remember { mutableStateOf(false) }
            MaterialTheme(
                colorScheme = if (isDarkTheme.value) darkColorScheme() else lightColorScheme()
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.rout
                    ) {
                        composable(Screen.Home.rout) {
                            HomeScreen(navController)
                        }
                        composable(
                            Screen.Details.rout + "/{movieId}",
                            arguments = listOf(
                                navArgument("movieId") { type = NavType.IntType }
                            )
                        ) { backStackEntry ->
                            DetailsScreen()
                        }
                    }
                }
            }
        }
    }
}