package com.example.composecarrent.ui.theme.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import com.example.composecarrent.ui.theme.bottomTopNavigation.BottomNavItemLine
import com.example.composecarrent.ui.theme.bottomTopNavigation.TopBar
import com.example.composecarrent.ui.theme.data.CarDataModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun MainScreen(favCars: Set<Int>, onFavCarChange: (Int) -> Unit) {
    val drawerState = rememberDrawerState(DrawerValue.Closed) // ( состояние, открыто по умолчанию )
    var favCarList by remember { mutableStateOf(listOf<CarDataModel>()) }
    val coroutineScope = rememberCoroutineScope()
    val db = Firebase.firestore

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
                TopBar(
                    onDrawerChange ={
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }
                )
            },
            bottomBar = {
                BottomNavItemLine()   // передача в Scaffold нижнего меню
            }
        ) { padding ->

            MainScreenBody(
                carList,
                modifier = Modifier.padding(padding),
                favCars,
                onFavCarChange = onFavCarChange
            )
        }

        fun updateFavCarBD(list : List<CarDataModel>, favCars: Set<Int>) {

            favCarList = list.filter { it.id in favCars }

            favCarList.forEach { item ->
                val docId = db.collection("favCars").document(item.id.toString())

                docId.get().addOnSuccessListener { doc ->
                    if (!doc.exists()) {
                        docId.set(item)
                    } else {
                        docId.delete()
                    }
                }
            }
        }
    }
}