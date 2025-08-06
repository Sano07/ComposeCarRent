package com.example.composecarrent.ui.theme.login_screen

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.composecarrent.R

@Composable
fun LoginScreen( viewModel: AuthLogicViewModel = viewModel(), navController: NavController) {

    var email by remember { mutableStateOf("") }       // cостояние отслеживает изменение имейла
    var password by remember { mutableStateOf("") }    // cостояние отслеживает изменение пароля
    val context = LocalContext.current                      // получение контекста текущей активити для передачи в Тост
    val registrationStatus = viewModel.registrationStatus   // получение из вью модел статуса регистрации

        // наблюдение за статусом регистрации и дальнейшая логика
    LaunchedEffect(registrationStatus) {
        if (registrationStatus != null && registrationStatus != "success") {
            Toast.makeText(
                context,
                "Ошибка: $registrationStatus",
                Toast.LENGTH_SHORT
            ).show()
        } else if(registrationStatus == "success") {
            navController.navigate("home_screen") {
                popUpTo("registration_screen") { inclusive = true }  // тема которая не пускает назад на экран регистрации в случае успеха
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column() {
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
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Your email") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Your password") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(150.dp))
                Column() {
                Button(
                    onClick = {
                        viewModel.register(email, password)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(100.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    border = ButtonDefaults.outlinedButtonBorder(enabled = true)
                )
                {
                    Text(
                        text = "LogIn",
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Button(
                    onClick = {

                    },
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(100.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    border = ButtonDefaults.outlinedButtonBorder(enabled = true)
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