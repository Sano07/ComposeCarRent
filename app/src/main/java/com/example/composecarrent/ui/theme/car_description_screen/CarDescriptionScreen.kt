package com.example.composecarrent.ui.theme.car_description_screen

import android.graphics.Bitmap
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.composecarrent.ui.theme.bottomTopNavigation.DefaultTopBar

@Composable
fun CarDescriptionScreen(
    navController: NavController,
    onDecode: (String) -> Bitmap,
    selectedCarForDesc: MutableState<Int?>,
    selectedCategory: MutableState<String>
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DefaultTopBar("Details")
        },
        bottomBar = {
            CarDescriptionScreenBottomBar(onStepBack = { navController.popBackStack() })
        }
    ) { padding ->

        CarDescriptionScreenBody(
            modifier = Modifier.padding(padding), onDecode, selectedCarForDesc, selectedCategory
        )
    }
}