package com.example.composecarrent.ui.theme.login_screen

import android.view.MotionEvent
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.composecarrent.R
import com.example.composecarrent.ui.theme.loaders.StartLoader

@Composable
fun LoginScreen(
    email: MutableState<String>,
    password: MutableState<String>,
    onFavCarUpdate: (Set<Int>) -> Unit,
    favCar: Set<Int>,
    isAdmin: MutableState<Boolean>,
    viewModel: AuthLogicViewModel = viewModel(),
    navController: NavController
) {

    val context =
        LocalContext.current                      // получение контекста текущей активити для передачи в Тост
    val registrationStatus =
        viewModel.registrationStatus   // получение из вью модел статуса регистрации
    val signInStatus = viewModel.signInStatus  // получение из вью статуса авторизации
    val showLoader = remember { mutableStateOf(true) }

    var isPressedReg by remember { mutableStateOf(false) }
    var isPressedLogIn by remember { mutableStateOf(false) }
    val scaleReg by animateFloatAsState(
        if (isPressedReg) 0.9f else 1f,
    )
    val scaleLog by animateFloatAsState(
        if (isPressedLogIn) 0.9f else 1f,
    )

    LaunchedEffect(registrationStatus) {
        if (registrationStatus != null && registrationStatus != "success") {
            Toast.makeText(
                context,
                "Ошибка: $registrationStatus",
                Toast.LENGTH_SHORT
            ).show()
        } else if (registrationStatus == "success") {
            navController.navigate("home_screen") {
                popUpTo("registration_screen") {
                    inclusive = true
                }  // тема которая не пускает назад на экран регистрации в случае успеха
            }
        }
    }

    // наблюдение за статусом входа в приложение
    LaunchedEffect(signInStatus) {
        if (signInStatus != null && signInStatus != "success") {
            Toast.makeText(
                context,
                "Ошибка: $signInStatus",
                Toast.LENGTH_SHORT
            ).show()
        } else if (signInStatus == "success") {
            navController.navigate("home_screen") {
                popUpTo("registration_screen") {
                    inclusive = true
                }  // тема которая не пускает назад на экран регистрации в случае успеха
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Image(
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.im_car_rent_logo),
                contentDescription = "Car_Rent_Logo"
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text("Your email") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text("Your password") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(150.dp))
                Column {
                    Box(
                        modifier = Modifier
                            .graphicsLayer(
                                scaleX = scaleReg,
                                scaleY = scaleReg
                            )
                            .pointerInteropFilter {
                                when (it.action) {
                                    MotionEvent.ACTION_DOWN -> isPressedReg = true
                                    MotionEvent.ACTION_UP -> {
                                        isPressedReg = false
                                    }

                                    MotionEvent.ACTION_CANCEL -> isPressedReg = false
                                }
                                false
                            }
                    ) {
                        Button(
                            onClick = {
                                viewModel.register(email.value, password.value)
                                isPressedReg = false
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .height(70.dp),
                            colors = ButtonDefaults.buttonColors(Color.White),
                            border = BorderStroke(2.dp, Color.Black)
                        )
                        {
                            Text(
                                text = "Registration",
                                color = Color.Black
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Box(
                        modifier = Modifier
                            .graphicsLayer(
                                scaleX = scaleLog,
                                scaleY = scaleLog
                            )
                            .pointerInteropFilter {
                                when (it.action) {
                                    MotionEvent.ACTION_DOWN -> isPressedLogIn = true
                                    MotionEvent.ACTION_UP -> {
                                        isPressedLogIn = false
                                    }

                                    MotionEvent.ACTION_CANCEL -> isPressedLogIn = false
                                }
                                false
                            }
                    ) {
                        Button(
                            onClick = {
                                viewModel.signIn(email.value, password.value)
                                isPressedLogIn = false
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .height(70.dp),
                            colors = ButtonDefaults.buttonColors(Color.White),
                            border = BorderStroke(2.dp, Color.Black)
                        )
                        {
                            Text(
                                text = "SignIn",
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}
//}