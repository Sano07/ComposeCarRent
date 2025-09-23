package com.example.composecarrent.ui.theme.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.composecarrent.ui.theme.bottomTopNavigation.BottomNavItemLine
import com.example.composecarrent.ui.theme.bottomTopNavigation.TopBar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun MainScreen(
    onFavCarUpdate: (Set<Int>) -> Unit,
    selectedFavCars: List<Int>,
    isAdmin: MutableState<Boolean>,
    drawerState: DrawerState,
    selectedItem: MutableState<String>,
    clicked: MutableState<Boolean>,
    navController: NavController,
    favCars: Set<Int>,
    onFavCarChange: (Int) -> Unit
) {
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

            fun checkFavCars(onResult: (Set<Int>) -> Unit) {
                val uid = Firebase.auth.currentUser!!.uid
                val email = Firebase.auth.currentUser!!.email
                val db = Firebase.firestore

                val userEmail = mapOf("email" to email)
                db.collection("users").document(uid).set(userEmail, SetOptions.merge())

                db.collection("users")
                    .document(uid)
                    .collection("favCars")
                    .get().addOnSuccessListener { result ->
                        val favId = result.documents.map { it.id.toInt() }.toSet()
                        onResult(favId)
                    }
            }

            fun checkIsAdmin(isAdmin: (Boolean) -> Unit) {
                val userUid = Firebase.auth.currentUser!!.uid
                Firebase.firestore.collection("admin")
                    .document(userUid).get().addOnSuccessListener {
                        isAdmin((it.get("admin")) as Boolean)
                    }
            }

            LaunchedEffect(Unit) {
                checkFavCars { result ->
                    onFavCarUpdate(favCars + result)
                }
            }

            LaunchedEffect(Unit) {
                checkIsAdmin { admin ->
                    isAdmin.value = admin
                }
            }

            MainScreenBody(
                navController = navController,
                selectedFavCars,
                isAdmin,
                clicked,
                carList,
                modifier = Modifier.padding(padding),
                favCars,
                onFavCarChange = onFavCarChange
            )
        }
    }
}