package com.example.composecarrent.ui.theme.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composecarrent.R

@Preview(showBackground = true)
@Composable
fun MainScreen_Drawer() {
    val drawerState = rememberDrawerState(DrawerValue.Open) // ( состояние, открыто по умолчанию )
    ModalNavigationDrawer(
        drawerState = drawerState,   // передача состояния ( открыто по умолчанию)
        modifier = Modifier.fillMaxWidth(0.7f),  // дравер занимает 70% экрана
        drawerContent = {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                DrawerHeader()
                DrawerBody()
            }
        }
    ) {

    }


}