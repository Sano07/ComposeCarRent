package com.example.composecarrent.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composecarrent.ui.theme.favorite_screen.FavoriteScreen
import com.example.composecarrent.ui.theme.login_screen.LoginScreen
import com.example.composecarrent.ui.theme.main_screen.MainScreen
import com.example.composecarrent.ui.theme.main_screen.carList

@Composable
fun AppNavigation(
    favCar: Set<Int>,
    onFavCarChange: (Int) -> Unit
) {
    val favCarList = carList.filter { favCar.contains(it.id) }
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home_screen") {
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("home_screen") {
            MainScreen(
                navController = navController,
                favCar,
                onFavCarChange = onFavCarChange
            )
        }
        composable("favorite_screen") {
            FavoriteScreen(
                navController = navController,
                favCarList,
                onFavCarChange = onFavCarChange
            )
        }
    }
}