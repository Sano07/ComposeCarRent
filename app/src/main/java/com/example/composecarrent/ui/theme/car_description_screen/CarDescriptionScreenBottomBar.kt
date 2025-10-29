package com.example.composecarrent.ui.theme.car_description_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecarrent.R

@Composable
fun CarDescriptionScreenBottomBar(onStepBack: () -> Unit) {
    val colorWhiteBack = colorResource(id = R.color.white2)

    Card {
        Row(modifier = Modifier.fillMaxWidth().background(colorWhiteBack).height(120.dp)) {
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(15.dp)
                    .height(60.dp),
                onClick = {
                    onStepBack()
                },
                colors = ButtonDefaults.buttonColors(Color.White),
                border = BorderStroke(2.dp, Color.Black)
            ) {
                Text(
                    text = "Back",
                    color = Color.Black,
                    fontSize = 18.sp
                )
            }
            Button(
                onClick = {
                    // какая то логика для заказа машины
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(Color.White),
                border = BorderStroke(2.dp, Color.Black)
            ) {
                Text(text = "Rent", color = Color.Black, fontSize = 18.sp)
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
    }
}