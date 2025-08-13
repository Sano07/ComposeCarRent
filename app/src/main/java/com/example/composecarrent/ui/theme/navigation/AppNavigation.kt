package com.example.composecarrent.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composecarrent.ui.theme.favorite_screen.FavoriteScreen
import com.example.composecarrent.ui.theme.login_screen.LoginScreen
import com.example.composecarrent.ui.theme.main_screen.MainScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home_screen") {
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("home_screen") {
            MainScreen()
        }
        composable("favorite_screen"){
            )
        }
    }
}