package com.example.composecarrent.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composecarrent.ui.theme.favorite_screen.FavoriteScreenBody
import com.example.composecarrent.ui.theme.login_screen.LoginScreen
import com.example.composecarrent.ui.theme.main_screen.MainScreen
import com.example.composecarrent.ui.theme.main_screen.carList

@Composable
fun AppNavigation(
    favCar : Set<Int>,
    onFavCarChange: (Int) -> Unit
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home_screen") {
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("home_screen") {
            MainScreen(
                favCar,
                onFavCarChange = onFavCarChange
            )
        }
        composable("favorite_screen"){
            FavoriteScreenBody( carList = carList, favCar,
                onFavCarChange = onFavCarChange
            )
        }
    }
}