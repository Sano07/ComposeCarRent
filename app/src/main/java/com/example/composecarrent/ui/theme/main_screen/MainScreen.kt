package com.example.composecarrent.ui.theme.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composecarrent.ui.theme.bottomNavigation.BottomNavItemLine

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(DrawerValue.Open) // ( состояние, открыто по умолчанию )
    ModalNavigationDrawer(
        drawerState = drawerState,   // передача состояния ( открыто по умолчанию)
        modifier = Modifier.fillMaxWidth(),
        drawerContent = {
            Column(
                modifier = Modifier.fillMaxWidth(0.7f) // дравер занимает 70% экрана
            ) {
                DrawerHeader()
                DrawerBody()
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomNavItemLine()   // передача в Scaffold нижнего меню
            }
        ) {  }

    }
}
