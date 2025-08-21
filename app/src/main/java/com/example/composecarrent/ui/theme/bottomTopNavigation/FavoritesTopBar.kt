package com.example.composecarrent.ui.theme.bottomTopNavigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesTopBar() {
    CenterAlignedTopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = { Text(text = "Favorite cars") },
    )
}