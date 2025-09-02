package com.example.composecarrent.ui.theme.settings_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecarrent.R
import com.example.composecarrent.ui.theme.WhiteLine

@Preview(showBackground = true)
@Composable
fun SettingsScreenBody() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Card(
                modifier = Modifier
                    .padding(top = 50.dp, start = 35.dp)
            ) {
                Text(
                    modifier = Modifier.background(Color.White),
                    text = "admin@gmail.com",
                    color = Color.Black,
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
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color.White),
                    border = ButtonDefaults.outlinedButtonBorder(enabled = true)
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
                    fontSize = 14.sp
                )
            }
            Row(
                modifier = Modifier
                    .padding(start = 30.dp),
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
                    border = ButtonDefaults.outlinedButtonBorder(enabled = true),
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
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color.White),
                    border = ButtonDefaults.outlinedButtonBorder(enabled = true),
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

