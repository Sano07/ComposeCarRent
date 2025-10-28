package com.example.composecarrent.ui.theme.navigation

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composecarrent.ui.theme.admin_panel.AdminPanelScreen
import com.example.composecarrent.ui.theme.car_description_screen.CarDescriptionScreen
import com.example.composecarrent.ui.theme.favorite_screen.FavoriteScreen
import com.example.composecarrent.ui.theme.login_screen.LoginScreen
import com.example.composecarrent.ui.theme.main_screen.MainScreen
import com.example.composecarrent.ui.theme.settings_screen.SettingsScreen
//import com.example.composecarrent.ui.theme.splash_screen.SplashScreen

@SuppressLint("UnrememberedMutableState")
@Composable
fun AppNavigation(
    favCar: Set<Int>,
    onFavCarUpdate: (Set<Int>) -> Unit,
    onFavCarChange: (Int) -> Unit
) {
    val email = remember { mutableStateOf("admin@admin.com") }       // cостояние отслеживает изменение имейла
    val password = remember { mutableStateOf("123456789") }    // cостояние отслеживает изменение пароля
    //val favCarList = carList.filter { favCar.contains(it.id) }
    val navController = rememberNavController()
    val clicked = remember { mutableStateOf(true) }
    val selectedItem = remember { mutableStateOf("Home") }
    val drawerState = rememberDrawerState(DrawerValue.Closed) // ( состояние, открыто по умолчанию )
    val isAdminState = remember { mutableStateOf(false) }
    val selectedFavCars by remember { mutableStateOf<List<Int>>(emptyList()) }
    val selCategory = remember { mutableStateOf("Sedans") }
    val selectedCarForDesc = remember { mutableStateOf<Int?>(null) }

    // расшифровка картинки из base64 в Image
    val onDecode = fun(icon: String): Bitmap {
        val base64DecodeImage = Base64.decode(icon, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(base64DecodeImage, 0, base64DecodeImage.size)
        return bitmap
    }

    NavHost(navController = navController, startDestination = "login") {
        //composable("splash") {
        //    SplashScreen(
        //        onLoginScreen = { navController.navigate("login") }
        //    )
        //}
        composable("login") {
            LoginScreen(email, password, onFavCarUpdate, favCar = favCar, isAdminState, navController = navController)
        }
        composable("home_screen") {
            MainScreen(
                onFavCarUpdate,
                selectedFavCars,
                isAdminState,
                selectedCarForDesc,
                onDecode,
                drawerState,
                selectedItem,
                selCategory,
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
                onFavCarChange = onFavCarChange,
                onDecode
            )
        }
        composable("settings_screen") {
            SettingsScreen(
                email,
                password,
                isAdminState,
                selectedItem,
                navController = navController
            )
        }
        composable("admin_screen") {
            AdminPanelScreen(navController = navController)
        }
        composable("desc_screen") {
            CarDescriptionScreen(navController = navController, onDecode, selectedCarForDesc, selCategory)
        }
    }
}
