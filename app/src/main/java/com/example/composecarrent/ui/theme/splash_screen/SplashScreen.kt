package com.example.composecarrent.ui.theme.splash_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composecarrent.ui.theme.loaders.StartLoader
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onLoginScreen: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(Color.White), contentAlignment = Alignment.Center) {
        StartLoader(modifier = Modifier.size(300.dp))
    }

    LaunchedEffect(Unit) {
        delay(5000)
        onLoginScreen()
    }
}