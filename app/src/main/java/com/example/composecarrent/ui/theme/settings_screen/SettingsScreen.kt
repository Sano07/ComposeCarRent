package com.example.composecarrent.ui.theme.settings_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.composecarrent.ui.theme.bottomTopNavigation.BottomNavItemLine
import com.example.composecarrent.ui.theme.bottomTopNavigation.DefaultTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    email: MutableState<String>,
    password: MutableState<String>,
    isAdmin: MutableState<Boolean>,
    selectedItem: MutableState<String>,
    navController: NavController
) {
    fun resetStates() {
        isAdmin.value = false
        selectedItem.value = "Home"
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DefaultTopBar("Settings")
        },
        bottomBar = {
            BottomNavItemLine(
                selectedItem,
                navController = navController
            )
        }
    ) { padding ->

        SettingsScreenBody(
            email,
            password,
            isAdmin,
            modifier = Modifier.padding(padding),
            onLogOut = { navController.navigate("login") },
            onAddCar = { navController.navigate("admin_screen") },
            onReset = { resetStates() }
        )
    }
}



