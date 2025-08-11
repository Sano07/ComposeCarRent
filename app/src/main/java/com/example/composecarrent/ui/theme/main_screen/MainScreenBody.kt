package com.example.composecarrent.ui.theme.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecarrent.R
import com.example.composecarrent.ui.theme.data.CarDataModel

var favStatus by mutableStateOf(true)
    private set

val carList = listOf(
    CarDataModel("", "Vaz", "911", "1000", "10", "29", "Механика", "Бензин", "Киев"),
    CarDataModel("", "Skoda", "911", "1000", "10", "29", "Механика", "Бензин", "Киев"),
    CarDataModel("", "Skoda", "911", "1000", "10", "29", "Механика", "Бензин", "Киев"),
    CarDataModel("", "Skoda", "911", "1000", "10", "29", "Механика", "Бензин", "Киев"),
    CarDataModel("", "Skoda", "911", "1000", "10", "29", "Механика", "Бензин", "Киев"),
    CarDataModel("", "Skoda", "911", "1000", "10", "29", "Механика", "Бензин", "Киев"),
    CarDataModel("", "Skoda", "911", "1000", "10", "29", "Механика", "Бензин", "Киев")
)


@Composable
fun MainScreenBody(list: List<CarDataModel>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(list) { _, item ->
            CarCards(item)
        }
    }
}

@Composable
fun CarCards(item: CarDataModel) {

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
                    text = item.mark
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                ) {
                    Text(
                        fontSize = 15.sp,
                        color = colorGrey,
                        text = item.model
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    fontSize = 20.sp,
                    color = colorGreen,
                    fontWeight = FontWeight.Bold,
                    text = "${item.coast}$"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                ) {
                    Image(
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .size(15.dp),
                        painter = painterResource(id = R.drawable.ic_consumption),
                        contentDescription = "Пробег"
                    )
                    Text(
                        color = colorGrey,
                        text = "${item.consumption}км."
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Image(
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .size(15.dp),
                        painter = painterResource(id = R.drawable.ic_transmision),
                        contentDescription = "Коробка"
                    )
                    Text(
                        color = colorGrey,
                        text = item.transmission
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                ) {
                    Image(
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .size(15.dp),
                        painter = painterResource(id = R.drawable.ic_fuel),
                        contentDescription = "Бензин"
                    )
                    Text(
                        color = colorGrey,
                        text = "${item.fuel}л."
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .size(15.dp),
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = "Город"
                    )
                    Text(
                        color = colorGrey,
                        text = item.location
                    )
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 10.dp)
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


