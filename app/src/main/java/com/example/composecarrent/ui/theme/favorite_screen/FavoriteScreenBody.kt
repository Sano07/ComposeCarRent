package com.example.composecarrent.ui.theme.favorite_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun FavoriteScreenBody(carList : List<CarDataModel>, favCars: Set<Int>, onFavCarChange: (Int) -> Unit) {

    val favCarList = carList.filter { favCars.equals(it.id) }

    LazyColumn(
    ) {
        itemsIndexed(favCarList.toList()) { _, item ->
            FavCarCards(item, favCarsDelete = { onFavCarChange(item.id) })
        }
    }
}

@Composable
fun FavCarCards(list: CarDataModel, favCarsDelete: () -> Unit) {

    val colorGreen = colorResource(id = R.color.green)
    val colorGrey = colorResource(id = R.color.grey)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column {
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
                        text = list.mark
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                    ) {
                        Text(
                            fontSize = 15.sp,
                            color = colorGrey,
                            text = list.model
                        )
                    }
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        fontSize = 20.sp,
                        color = colorGreen,
                        fontWeight = FontWeight.Bold,
                        text = "${list.coast}$"
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
                            text = "${list.consumption}т.км."
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
                            text = list.transmission
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
                            text = list.fuel
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
                            text = list.location
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        // какая то логика для заказа машины
                    },
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .width(120.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    border = ButtonDefaults.outlinedButtonBorder(enabled = true)
                ) {
                    Text(text = "Rent", color = Color.Black)
                }
                Button(
                    onClick = {
                        favCarsDelete()
                    },
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .width(120.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    border = ButtonDefaults.outlinedButtonBorder(enabled = true)
                ) {
                    Text(text = "Delete", color = Color.Black)
                }
            }
        }
    }
}