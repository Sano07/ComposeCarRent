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
            InfoWindow(isShowInfoWindow)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoWindow(isShowInfoWindow: MutableState<Boolean>) {
    if (isShowInfoWindow.value) {
        BasicAlertDialog(
            onDismissRequest = { isShowInfoWindow.value = false },
            modifier = Modifier.size(250.dp),
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
        ) {
            Box(
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(15.dp)
                    .width(260.dp)
            ) {
                Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Тут какая то логика заказа авто :) я ее пока не придумал",
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Spacer(modifier = Modifier.weight(0.5f))
                    Button(
                        onClick = { isShowInfoWindow.value = false },
                        modifier = Modifier.fillMaxWidth().height(80.dp),
                        colors = ButtonDefaults.buttonColors(Color.White),
                        border = BorderStroke(2.dp, Color.Black)
                    ) {
                        Text(
                            text = "OK",
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}