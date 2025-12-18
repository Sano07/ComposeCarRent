package com.example.composecarrent.ui.theme.car_description_screen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.composecarrent.ui.theme.bottomTopNavigation.DefaultTopBar
import com.example.composecarrent.ui.theme.info_window.InfoWindow

@SuppressLint("UnrememberedMutableState")
@Composable
fun CarDescriptionScreen(
    navController: NavController,
    onDecode: (String) -> Bitmap,
    selectedCarForDesc: MutableState<Int?>,
    selectedCategory: MutableState<String>,
    isShowInfoWindow: MutableState<Boolean>,
) {
    val showDescLoader = remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (!showDescLoader.value) {
                DefaultTopBar("Details")
            }
        },
        bottomBar = {
            if (!showDescLoader.value) {
                CarDescriptionScreenBottomBar(onStepBack = { navController.popBackStack() }, isShowInfoWindow)
            }
            InfoWindow(
                visible = isShowInfoWindow.value,
                text = "а тут какая то логика заказа авто :) я ее еще не придумал",
                onDismissRequest = { isShowInfoWindow.value = false },
                onButtonClick = {isShowInfoWindow.value = false }
            )
        }
    ) { padding ->

        CarDescriptionScreenBody(
            modifier = Modifier.padding(padding),
            showDescLoader,
            onDecode,
            selectedCarForDesc,
            selectedCategory,
            isShowInfoWindow
        )
    }
}