package com.example.composecarrent.ui.theme.main_screen

import android.content.ClipData.Item
import android.graphics.Paint.Align
import android.widget.ImageView
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toDrawable
import com.example.composecarrent.R

var favStatus by mutableStateOf<Boolean>(true)
    private set

/*
@Composable
fun MainScreenBody(list: List<CarCardModel>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(list) { _, item ->
            CarCards(item)
        }
    }
}
*/

@Preview(showBackground = true)
@Composable
fun CarCards() {

    val colorGreen = colorResource(id = R.color.green)
    val colorGrey = colorResource(id = R.color.grey)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(5.dp),
                painter = painterResource(id = R.drawable.im_car),
                contentDescription = "машинка для примера",
                contentScale = ContentScale.Inside,

                )
            Column {
                Text(
                    modifier = Modifier.padding(5.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorGrey,
                    text = "Bugatti"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                ) {
                    Text(
                        fontSize = 15.sp,
                        color = colorGrey,
                        text = "La Voiture Noire"
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    fontSize = 20.sp,
                    color = colorGreen,
                    fontWeight = FontWeight.Bold,
                    text = "6000$"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                ) {
                    Image(
                        modifier = Modifier.padding(end = 5.dp).size(15.dp),
                        painter = painterResource(id = R.drawable.ic_consumption),
                        contentDescription = "Пробег"
                    )
                    Text(
                        color = colorGrey,
                        text = "20.тыс."
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Image(
                        modifier = Modifier.padding(end = 5.dp).size(15.dp),
                        painter = painterResource(id = R.drawable.ic_transmision),
                        contentDescription = "Коробка"
                    )
                    Text(
                        color = colorGrey,
                        text = "Авто"
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                ) {
                    Image(
                        modifier = Modifier.padding(end = 5.dp).size(15.dp),
                        painter = painterResource(id = R.drawable.ic_fuel),
                        contentDescription = "Бензин"
                    )
                    Text(
                        color = colorGrey,
                        text = "22.3л"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        modifier = Modifier.padding(end = 5.dp).size(15.dp),
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = "Город"
                    )
                    Text(
                        color = colorGrey,
                        text = "Киев"
                    )
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier.align(Alignment.End).padding(end = 10.dp)
                ) {
                    when (favStatus) {
                        true -> Icon(
                            modifier = Modifier.size(50.dp),
                            painter = painterResource(R.drawable.ic_favorite_red),
                            contentDescription = "Добавлено в избранное"
                        )

                        false -> Icon(
                            modifier = Modifier.size(50.dp),
                            painter = painterResource(R.drawable.ic_favorite_black),
                            contentDescription = "Не добавлено в избранное",
                        )
                    }
                }
            }
        }
    }

}


