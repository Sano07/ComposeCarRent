package com.example.composecarrent.ui.theme.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composecarrent.ui.theme.favorite_screen.FavoriteScreen
import com.example.composecarrent.ui.theme.login_screen.LoginScreen
import com.example.composecarrent.ui.theme.main_screen.MainScreen
import com.example.composecarrent.ui.theme.main_screen.carList
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun AppNavigation(
    favCar: Set<Int>,
    onFavCarChange: (Int) -> Unit
) {
    val favCarList = carList.filter { favCar.contains(it.id) }
    val navController = rememberNavController()
    val clicked = remember { mutableStateOf(true) }
    val selectedItem = remember { mutableStateOf("Home") }
    val drawerState = rememberDrawerState(DrawerValue.Closed) // ( состояние, открыто по умолчанию )
    val isAdminState = remember { mutableStateOf(false) }


    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(isAdminState, navController = navController)
        }
        composable("home_screen") {
            MainScreen(
                isAdminState,
                drawerState,
                selectedItem,
                clicked,
                navController = navController,
                favCar,
                onFavCarChange = onFavCarChange
            )
        }
        composable("favorite_screen") {
            FavoriteScreen(
                selectedItem,
                favCar,
                navController = navController,
                favCarList,
                onFavCarChange = onFavCarChange
            )
        }
    }
}