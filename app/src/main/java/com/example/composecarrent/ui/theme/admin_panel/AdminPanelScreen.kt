package com.example.composecarrent.ui.theme.admin_panel

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.composecarrent.ui.theme.bottomTopNavigation.DefaultTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdminPanelScreen(navController: NavController) {

    val mark = remember { mutableStateOf("") }
    val modelYear = remember { mutableStateOf("") }
    val coast = remember { mutableStateOf("") }
    val consumption = remember { mutableStateOf("") }
    val mileage = remember { mutableStateOf("") }
    val fuel = remember { mutableStateOf("") }
    val transmission = remember { mutableStateOf("") }
    val location = remember { mutableStateOf("") }
    val carId = remember { mutableStateOf("") }
    val category = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DefaultTopBar("Admin panel")
        }
    ) { padding ->

        AdminPanelScreenBody(
            modifier = Modifier.padding(padding),
            onStepBack = { navController.popBackStack() }
        )
    }
}