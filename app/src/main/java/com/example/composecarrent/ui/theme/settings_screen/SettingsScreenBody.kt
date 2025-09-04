package com.example.composecarrent.ui.theme.settings_screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SettingsScreenBody(
    modifier: Modifier = Modifier,
    onLogOut: () -> Unit,
    onAddCar: () -> Unit,
    viewModel: SettingsScreenViewModel = viewModel(),
) {

    val context =
        LocalContext.current
    val logOutStatus = viewModel.logOutStatus // отслеживание статуса выхода из аккаунта

    LaunchedEffect(logOutStatus) {
        if (logOutStatus == "success") {
            Toast.makeText(
                context,
                "Log Out Success",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Box(
        modifier = modifier
    ) {
        Column {
            Card(
                modifier = Modifier
                    .padding(start = 35.dp, top = 50.dp)
            ) {
                Text(
                    modifier = Modifier.background(Color.White),
                    text = "Current user",
                    color = Color.DarkGray,
                    fontSize = 16.sp
                )
            }
            Card(
                modifier = Modifier
                    .padding(top = 15.dp, start = 30.dp)
            ) {
                Text(
                    modifier = Modifier.background(Color.White),
                    text = "admin@gmail.com",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(60.dp),
                    onClick = {
                        viewModel.logOut()
                        onLogOut()
                    },
                    colors = ButtonDefaults.buttonColors(Color.White),
                    border = BorderStroke(2.dp, Color.Black)
                ) {
                    Text(
                        text = "Log Out",
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    border = ButtonDefaults.outlinedButtonBorder(enabled = true),
                ) {
                    Text(
                        text = "Delete Account",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.LightGray)
            )
            Card(
                modifier = Modifier
                    .padding(start = 35.dp, top = 30.dp)
            ) {
                Text(
                    modifier = Modifier.background(Color.White),
                    text = "Balance",
                    color = Color.DarkGray,
                    fontSize = 16.sp
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 15.dp, start = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth(0.5f),
                    text = "1000$",
                    color = Color.Green,
                    fontSize = 50.sp
                )
                Spacer(modifier = Modifier.width(20.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(end = 15.dp),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color.White),
                    border = BorderStroke(2.dp, Color.Black)
                ) {
                    Text(
                        text = "Fill",
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.LightGray),
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(15.dp)
                        .height(60.dp),
                    onClick = {
                        onAddCar()
                    },
                    colors = ButtonDefaults.buttonColors(Color.White),
                    border = BorderStroke(2.dp, Color.Black)
                ) {
                    Text(
                        text = "Add new car",
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}


