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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.composecarrent.ui.theme.bottomTopNavigation.BottomNavItemLine
import com.example.composecarrent.ui.theme.bottomTopNavigation.TopBar
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun MainScreen(
    selectedItem: MutableState<String>,
    clicked: MutableState<Boolean>,
    navController: NavController,
    favCars: Set<Int>,
    onFavCarChange: (Int) -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed) // ( состояние, открыто по умолчанию )
    //var favCarList by remember { mutableStateOf(listOf<CarDataModel>()) }
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,   // передача состояния ( открыто по умолчанию)
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .zIndex(100f) // дравер занимает 70% экрана
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
                    onDrawerChange = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }
                )
            },
            bottomBar = {
                BottomNavItemLine(selectedItem, navController = navController)   // передача в Scaffold нижнего меню
            }
        ) { padding ->

            MainScreenBody(
                clicked,
                carList,
                modifier = Modifier.padding(padding),
                favCars,
                onFavCarChange = onFavCarChange
            )
        }

        /*
        fun addCarToFavorite(list: List<CarDataModel>, favCars: Set<Int>) {

            favCarList = carList.filter { favCars.contains(it.id) }

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
        */
    }
}