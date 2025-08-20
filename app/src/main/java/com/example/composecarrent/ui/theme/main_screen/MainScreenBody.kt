package com.example.composecarrent.ui.theme.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecarrent.R
import com.example.composecarrent.ui.theme.data.CarDataModel

val carList = listOf(
    CarDataModel(
        id = 1,
        carIcon = "",
        mark = "test",
        model = "test",
        coast = "test",
        mileage = "test",
        consumption = "test",
        transmission = "test",
        fuel = "test",
        location = "test"
    )
)


@Composable
fun MainScreenBody(list: List<CarDataModel>, modifier: Modifier = Modifier, favCars: Set<Int>, onFavCarChange: (Int) -> Unit) {

    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(list) { index, item ->
            val isFav = favCars.contains(index)
            CarCards(item, isFav, onFavCarChange = { onFavCarChange(index) })
        }
    }
}

@Composable
fun CarCards(item: CarDataModel, isFav: Boolean, onFavCarChange: (String) -> Unit) {

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
                        text = "${item.consumption}т.км."
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
                        text = item.fuel
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
                    onClick = { onFavCarChange(item.id.toString() )},
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 10.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(50.dp),
                        painter = painterResource(R.drawable.ic_favorite),
                        contentDescription = "Добавлено в избранное",
                        tint = if (isFav) Color.Red else Color.Black
                    )
                }
            }
        }
    }
}


