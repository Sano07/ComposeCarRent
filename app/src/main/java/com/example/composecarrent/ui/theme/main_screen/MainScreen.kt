package com.example.composecarrent.ui.theme.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.example.composecarrent.ui.theme.bottomNavigation.BottomNavItemLine
import com.example.composecarrent.ui.theme.bottomNavigation.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(DrawerValue.Open) // ( состояние, открыто по умолчанию )

    ModalNavigationDrawer(
        drawerState = drawerState,   // передача состояния ( открыто по умолчанию)
        drawerContent = {
            Column(
                modifier = Modifier.fillMaxWidth(0.7f).zIndex(100f) // дравер занимает 70% экрана
            ) {
                DrawerHeader()
                DrawerBody()
            }
        },
        modifier = Modifier.zIndex(100f)
    ) {
        Scaffold(
            modifier = Modifier.zIndex(100f),
            topBar = {
                TopBar()
            },
            bottomBar = {
                BottomNavItemLine()   // передача в Scaffold нижнего меню
            }
        ) { padding ->

            MainScreenBody(
                carList,
                modifier = Modifier.padding(padding)
            )
        }
    }
}