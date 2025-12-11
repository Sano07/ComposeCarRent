package com.example.composecarrent.ui.theme.car_description_screen

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.composecarrent.R
import com.example.composecarrent.ui.theme.data.CarDataModel
import com.example.composecarrent.ui.theme.loaders.DescLoader
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay

@Composable
fun ImagePager(
    onDecode: (String) -> Bitmap,
    image1: Bitmap?,
    image2: Bitmap?,
    image3: Bitmap?
) {
    val images = listOfNotNull(image1, image2, image3)
    val pagerState = rememberPagerState(pageCount = { images.size })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        AsyncImage(
            model = images[page],
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
        )
    }
}


@Composable
fun CarDescriptionScreenBody(
    modifier: Modifier = Modifier,
    showLoader: MutableState<Boolean>,
    onDecode: (String) -> Bitmap,
    selectedCarForDesc: MutableState<Int?>,
    selectedCategory: MutableState<String>
) {
    val colorGrey = colorResource(id = R.color.grey)
    val colorGreen = colorResource(id = R.color.green)
    val scrollStateText = rememberScrollState()


    val db = Firebase.firestore
    var selectedCarDescription by remember { mutableStateOf<CarDataModel?>(null) }

    LaunchedEffect(Unit) {
        db.collection("cars")
            .document(selectedCategory.value)
            .collection("cars")
            .document(selectedCarForDesc.value.toString())
            .get()
            .addOnSuccessListener { result ->
                selectedCarDescription = result.toObject(CarDataModel::class.java)
            }
        delay(2000) // минимум 2 секунды
        showLoader.value = false
    }

    val decod1 = selectedCarDescription?.let { onDecode(it.carIcon1) }
    val decod2 = selectedCarDescription?.let { onDecode(it.carIcon2) }
    val decod3 = selectedCarDescription?.let { onDecode(it.carIcon3) }

    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        if (showLoader.value) {
            DescLoader(modifier = Modifier.fillMaxSize().size(150.dp))
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollStateText)
                    .padding(5.dp)
            ) {
                ImagePager(
                    onDecode,
                    decod1,
                    decod2,
                    decod3
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            modifier = Modifier.padding(5.dp),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorGrey,
                            text = selectedCarDescription?.mark.toString()
                        )
                        Text(
                            modifier = Modifier.padding(start = 8.dp),
                            fontSize = 20.sp,
                            color = colorGrey,
                            text = selectedCarDescription?.model.toString()
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            fontSize = 25.sp,
                            color = colorGreen,
                            fontWeight = FontWeight.Bold,
                            text = "${selectedCarDescription?.coast.toString()}$"
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
                                text = "${selectedCarDescription?.consumption.toString()}л."
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
                                text = selectedCarDescription?.transmission.toString()
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
                                text = selectedCarDescription?.fuel.toString()
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
                                text = selectedCarDescription?.location.toString()
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
                                text = "${selectedCarDescription?.mileage.toString()} т.км."
                            )
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            fontSize = 15.sp,
                            color = colorGrey,
                            text = selectedCarDescription?.decription.toString()
                        )

                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}