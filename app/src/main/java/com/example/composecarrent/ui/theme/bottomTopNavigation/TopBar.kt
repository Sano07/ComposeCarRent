package com.example.composecarrent.ui.theme.bottomTopNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onDrawerChange: () -> Unit) {
    CenterAlignedTopAppBar(
            title = { Text(text = "Store", fontSize = 25.sp) },
            actions = {
                IconButton(
                    onClick = onDrawerChange,
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Меню",
                        tint = Color.Black,
                    )
                }


            }
        )
}