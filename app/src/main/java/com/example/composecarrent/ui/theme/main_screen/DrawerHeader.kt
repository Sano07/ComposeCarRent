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
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecarrent.R
import com.example.composecarrent.ui.theme.DarkBlue
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
// настройка хедера выдвижного дравера
fun DrawerHeader() {

    // подключение шрифта
    val logoFontFamily = FontFamily(
        Font(R.font.single_bold, FontWeight.Bold)
    )

    Column(
        Modifier.fillMaxWidth()
            .height(220.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(150.dp),
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
    }
}

