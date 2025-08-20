package com.example.composecarrent

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.composecarrent.ui.theme.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var favCars by remember { mutableStateOf(setOf<Int>()) }

            AppNavigation(
                favCar = favCars,
                onFavCarChange = { carId ->
                    favCars = if (favCars.contains(carId)) {
                        favCars - carId
                    } else {
                        favCars + carId
                    }
                }
            )
        }
    }
}
