package com.example.composecarrent.ui.theme.settings_screen

import android.view.MotionEvent
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composecarrent.R

@Composable
fun SettingsScreenBody(
    email: MutableState<String>,
    password: MutableState<String>,
    isAdmin: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    onLogOut: () -> Unit,
    onAddCar: () -> Unit,
    onReset: () -> Unit,
    viewModel: SettingsScreenViewModel = viewModel(),
) {

    val context =
        LocalContext.current
    val logOutStatus = viewModel.logOutStatus // отслеживание статуса выхода из аккаунта
    val deleteAccountStatus =
        viewModel.deleteAccountStatus // отслеживание статуса удаления аккаунта
    val fillAccountStatus = viewModel.fullAccountStatus
    var showDialog by remember { mutableStateOf(false) }
    val fillBalanceValue = viewModel.fillBalanceValue
    val colorGreen = colorResource(id = R.color.green)
    val colorWhiteBack = colorResource(id = R.color.white2)

    var isPressedLogOut by remember { mutableStateOf(false) }
    val scaleLogOut by animateFloatAsState(
        if (isPressedLogOut) 0.9f else 1f,
    )

    var isPressedDelete by remember { mutableStateOf(false) }
    val scaleDelete by animateFloatAsState(
        if (isPressedDelete) 0.9f else 1f,
    )

    var isPressedFill by remember { mutableStateOf(false) }
    val scaleFill by animateFloatAsState(
        if (isPressedFill) 0.9f else 1f,
    )

    var isPressedAdd by remember { mutableStateOf(false) }
    val scaleAdd by animateFloatAsState(
        if (isPressedAdd) 0.9f else 1f,
    )



    LaunchedEffect(logOutStatus) {
        if (logOutStatus == "success") {
            Toast.makeText(
                context,
                "Log Out Success",
                Toast.LENGTH_SHORT
            ).show()
            onReset()
        }
    }

    LaunchedEffect(deleteAccountStatus) {
        if (deleteAccountStatus != null && deleteAccountStatus != "success") {
            Toast.makeText(
                context,
                "Ошибка: $deleteAccountStatus",
                Toast.LENGTH_SHORT
            ).show()
        } else if (deleteAccountStatus == "success") {
            Toast.makeText(
                context,
                "Account was deleted successful",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    LaunchedEffect(fillAccountStatus) {
        if (fillAccountStatus != null && fillAccountStatus != "success") {
            Toast.makeText(
                context,
                "Ошибка: $fillAccountStatus",
                Toast.LENGTH_SHORT
            ).show()
        } else if (fillAccountStatus == "success") {
            Toast.makeText(
                context,
                "Filling successful",
                Toast.LENGTH_SHORT
            ).show()
        }
        viewModel.fullAccountStatus = null
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
                    modifier = Modifier.background(colorWhiteBack),
                    text = "Current user",
                    color = Color.DarkGray,
                    fontSize = 16.sp
                )
            }
            Card(
                modifier = Modifier
                    .padding(top = 15.dp, start = 30.dp)
                    .background(colorWhiteBack)
            ) {
                Text(
                    modifier = Modifier.background(colorWhiteBack),
                    text = email.value,
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
                Box(
                    modifier = Modifier
                        .graphicsLayer(
                            scaleX = scaleLogOut,
                            scaleY = scaleLogOut
                        )
                        .pointerInteropFilter {
                            when (it.action) {
                                MotionEvent.ACTION_DOWN -> isPressedLogOut = true
                                MotionEvent.ACTION_UP -> {
                                    isPressedLogOut = false
                                }

                                MotionEvent.ACTION_CANCEL -> isPressedLogOut = false
                            }
                            false
                        }
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(60.dp)
                            .background(colorWhiteBack),
                        onClick = {
                            viewModel.logOut()
                            onLogOut()
                            isPressedLogOut = false
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
                }
                Spacer(modifier = Modifier.width(5.dp))
                Box(
                    modifier = Modifier
                        .graphicsLayer(
                            scaleX = scaleDelete,
                            scaleY = scaleDelete
                        )
                        .pointerInteropFilter {
                            when (it.action) {
                                MotionEvent.ACTION_DOWN -> isPressedDelete = true
                                MotionEvent.ACTION_UP -> {
                                    isPressedDelete = false
                                }

                                MotionEvent.ACTION_CANCEL -> isPressedDelete = false
                            }
                            false
                        }
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(colorWhiteBack),
                        onClick = {
                            showDialog = true
                            isPressedDelete = false
                        },
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
                    modifier = Modifier.background(colorWhiteBack),
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
                        .background(colorWhiteBack)
                        .fillMaxWidth(0.5f),
                    text = "$fillBalanceValue $",
                    color = colorGreen,
                    fontSize = 50.sp
                )
                Spacer(modifier = Modifier.width(20.dp))
                Box(
                    modifier = Modifier
                        .graphicsLayer(
                            scaleX = scaleFill,
                            scaleY = scaleFill
                        )
                        .pointerInteropFilter {
                            when (it.action) {
                                MotionEvent.ACTION_DOWN -> isPressedFill = true
                                MotionEvent.ACTION_UP -> {
                                    isPressedFill = false
                                }

                                MotionEvent.ACTION_CANCEL -> isPressedFill = false
                            }
                            false
                        }
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(end = 15.dp),
                        onClick = {
                            viewModel.fillBalance()
                            viewModel.fullAccountStatus = "success"
                            isPressedFill = false
                        },
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
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.LightGray),
            )
            if (isAdmin.value) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .graphicsLayer(
                                scaleX = scaleAdd,
                                scaleY = scaleAdd
                            )
                            .pointerInteropFilter {
                                when (it.action) {
                                    MotionEvent.ACTION_DOWN -> isPressedAdd = true
                                    MotionEvent.ACTION_UP -> {
                                        isPressedAdd = false
                                    }

                                    MotionEvent.ACTION_CANCEL -> isPressedAdd = false
                                }
                                false
                            }
                    ) {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .padding(15.dp)
                                .height(60.dp),
                            onClick = {
                                onAddCar()
                                isPressedAdd = false
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
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Confirmation") },
            text = { Text("Are you sure you want to delete your account?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        viewModel.deleteAccount(email.value, password.value)
                        onLogOut()
                        onReset()
                    }
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false }
                ) {
                    Text("Back")
                }
            }
        )
    }
}


