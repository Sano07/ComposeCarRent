package com.example.composecarrent.ui.theme.favorite_screen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
    favCarList: List<CarDataModel>,
    onFavCarChange: (Int) -> Unit,
    onDecode: (String) -> Bitmap
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DefaultTopBar("Favorite cars")
        },
        bottomBar = {
            BottomNavItemLine(
                selectedItem,
                navController = navController
            )   // передача в Scaffold нижнего меню
        }
    ) { padding ->

        FavoriteScreenBody(
            modifier = Modifier.padding(padding),
            onFavCarChange = onFavCarChange,
            onDecode
        )

    }
}