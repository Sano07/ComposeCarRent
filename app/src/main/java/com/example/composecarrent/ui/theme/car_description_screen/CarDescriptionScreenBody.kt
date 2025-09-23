package com.example.composecarrent.ui.theme.car_description_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.composecarrent.R

val images = listOf(
    R.drawable.im_car,
    R.drawable.im_car,
    R.drawable.im_car,
)

@Composable
fun ImagePager() {

    val pagerState = rememberPagerState(pageCount = { images.size })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        Image(
            painterResource(id = images[page]),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        /*
        AsyncImage(
            model = images[page],
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().size(100.dp)
        )
         */
    }
}

@Composable
fun CarDescriptionScreenBody(modifier: Modifier = Modifier) {
    val colorGrey = colorResource(id = R.color.grey)
    val colorGreen = colorResource(id = R.color.green)
    val scrollStateText = rememberScrollState()

    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            ImagePager()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollStateText)
            ) {
                Column {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        modifier = Modifier.padding(5.dp),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorGrey,
                        text = "Lada Prioasdasd asdsdasdsadasd asdasdara"
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        fontSize = 20.sp,
                        color = colorGrey,
                        text = "Priora super ultra tachka , 2012"
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        fontSize = 25.sp,
                        color = colorGreen,
                        fontWeight = FontWeight.Bold,
                        text = "100 000$"
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .size(22.dp),
                            painter = painterResource(id = R.drawable.ic_consumption),
                            contentDescription = "Пробег"
                        )
                        Text(
                            fontSize = 20.sp,
                            color = colorGrey,
                            text = "50 т.км."
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .size(22.dp),
                            painter = painterResource(id = R.drawable.ic_transmision),
                            contentDescription = "Коробка"
                        )
                        Text(
                            fontSize = 20.sp,
                            color = colorGrey,
                            text = "Auto"
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .size(22.dp),
                            painter = painterResource(id = R.drawable.ic_fuel),
                            contentDescription = "Бензин"
                        )
                        Text(
                            fontSize = 20.sp,
                            color = colorGrey,
                            text = "Diesel"
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .size(22.dp),
                            painter = painterResource(id = R.drawable.ic_location),
                            contentDescription = "Город"
                        )
                        Text(
                            fontSize = 20.sp,
                            color = colorGrey,
                            text = "Kiev"
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .size(22.dp),
                            painter = painterResource(id = R.drawable.ic_mileage),
                            contentDescription = "Пробег"
                        )
                        Text(
                            fontSize = 20.sp,
                            color = colorGrey,
                            text = "22 тыс. км"
                        )
                    }
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        fontSize = 15.sp,
                        color = colorGrey,
                        text = "Практичний, надійний універсал," +
                                " з дуже економічним двигуном об'ємом дизельним" +
                                " 2.0 літра потужністю 170к.с\n" +
                                "Авто приїхало з Н" +
                                "імеччини в 2022 році," +
                                " має " +
                                "одного власника в Україні. " +
                                "Технічно автомобіль в чудовому стані. \n" +
                                "Комплектація: кл" +
                                "імат контро" +
                                "ль, круїз контроль, датчик світл" +
                                "а, датчик дощу, електро регулювання с" +
                                "идіння воді" +
                                "я, " +
                                "мультимедія з підтримкою Ap" +
                                "ple CarPl" +
                                "ay AndroidAut" +
                                "o, та інше." +
                        "Практичний, надійний універсал," +
                                " з дуже економічним двигуном об'ємом дизельним" +
                                " 2.0 літра потужністю 170к.с\n" +
                                "Авто приїхало з Н" +
                                "імеччини в 2022 році," +
                                " має " +
                                "одного власника в Україні. " +
                                "Технічно автомобіль в чудовому стані. \n" +
                                "Комплектація: кл" +
                                "імат контро" +
                                "ль, круїз контроль, датчик світл" +
                                "а, датчик дощу, електро регулювання с" +
                                "идіння воді" +
                                "я, " +
                                "мультимедія з підтримкою Ap" +
                                "ple CarPl" +
                                "ay AndroidAut" +
                                "o, та інше." +
                        "Практичний, надійний універсал," +
                                " з дуже економічним двигуном об'ємом дизельним" +
                                " 2.0 літра потужністю 170к.с\n" +
                                "Авто приїхало з Н" +
                                "імеччини в 2022 році," +
                                " має " +
                                "одного власника в Україні. " +
                                "Технічно автомобіль в чудовому стані. \n" +
                                "Комплектація: кл" +
                                "імат контро" +
                                "ль, круїз контроль, датчик світл" +
                                "а, датчик дощу, електро регулювання с" +
                                "идіння воді" +
                                "я, " +
                                "мультимедія з підтримкою Ap" +
                                "ple CarPl" +
                                "ay AndroidAut" +
                                "o, та інше." +
                        "Практичний, надійний універсал," +
                                " з дуже економічним двигуном об'ємом дизельним" +
                                " 2.0 літра потужністю 170к.с\n" +
                                "Авто приїхало з Н" +
                                "імеччини в 2022 році," +
                                " має " +
                                "одного власника в Україні. " +
                                "Технічно автомобіль в чудовому стані. \n" +
                                "Комплектація: кл" +
                                "імат контро" +
                                "ль, круїз контроль, датчик світл" +
                                "а, датчик дощу, електро регулювання с" +
                                "идіння воді" +
                                "я, " +
                                "мультимедія з підтримкою Ap" +
                                "ple CarPl" +
                                "ay AndroidAut" +
                                "o, та інше."
                    )

                }
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}