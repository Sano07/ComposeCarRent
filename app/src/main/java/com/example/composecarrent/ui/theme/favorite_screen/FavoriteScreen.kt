package com.example.composecarrent.ui.theme.favorite_screen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.composecarrent.ui.theme.bottomTopNavigation.BottomNavItemLine
import com.example.composecarrent.ui.theme.bottomTopNavigation.DefaultTopBar
import com.example.composecarrent.ui.theme.data.CarDataModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun FavoriteScreen(
    selectedItem: MutableState<String>,
    favCars: Set<Int>,
    navController: NavController,
    onFavCarChange: (Int) -> Unit,
    onDecode: (String) -> Bitmap
) {
    val showFavMainLoader = remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (!showFavMainLoader.value) {
                DefaultTopBar("Favorite cars")
            }
        },
        bottomBar = {
            if (!showFavMainLoader.value) {
                BottomNavItemLine(
                    selectedItem,
                    navController = navController
                )   // передача в Scaffold нижнего меню
            }
        }
    ) { padding ->

        FavoriteScreenBody(
            modifier = Modifier.padding(padding),
            onFavCarChange = onFavCarChange,
            onDecode,
            showFavMainLoader
        )

    }
}