package com.example.composecarrent.ui.theme.favorite_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.composecarrent.ui.theme.bottomTopNavigation.BottomNavItemLine
import com.example.composecarrent.ui.theme.data.CarDataModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun FavoriteScreen(navController: NavController, favCarList : List<CarDataModel>, onFavCarChange: (Int) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavItemLine(navController = navController)   // передача в Scaffold нижнего меню
        }
    ) { padding ->

        FavoriteScreenBody(
            favCarList,
            modifier = Modifier.padding(padding),
            onFavCarChange = onFavCarChange)
    }
}