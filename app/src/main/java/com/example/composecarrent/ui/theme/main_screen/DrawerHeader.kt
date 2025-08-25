package com.example.composecarrent.ui.theme.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecarrent.R
import com.google.firebase.auth.FirebaseAuth

@Composable
// настройка хедера выдвижного дравера
fun DrawerHeader() {

    val userEmail = FirebaseAuth.getInstance().currentUser?.email ?: ""

    // подключение шрифта
    val logoFontFamily = FontFamily(
        Font(R.font.single_bold, FontWeight.Bold)
    )

    Column(
        Modifier.fillMaxWidth()
            .height(240.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(160.dp),
            painter = painterResource(id = R.drawable.im_car_rent_logo),
            contentDescription = "Car_Rent_Logo"
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "SANO CAR RENT",
            color = Color.Black,
            fontSize = 30.sp,
            fontFamily = logoFontFamily,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Hello, $userEmail!",
            color = Color.Black,
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

