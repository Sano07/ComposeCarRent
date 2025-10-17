package com.example.composecarrent.ui.theme.car_description_screen

import android.graphics.Bitmap
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.composecarrent.ui.theme.data.CarDataModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun ImagePager(
    onDecode: (String) -> Bitmap,
    image1: String,
    image2: String,
    image3: String
) {

    val images = listOf(image1, image2, image3)
    val decodedImages = mutableListOf<Bitmap>()

    images.forEach { image ->
        decodedImages.add(onDecode(image))
    }

    val pagerState = rememberPagerState(pageCount = { images.size })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        AsyncImage(
            model = decodedImages[page],
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().size(100.dp)
        )
    }
}

@Composable
fun CarDescriptionScreenBody(
    modifier: Modifier = Modifier,
    onDecode: (String) -> Bitmap,
    selectedCarForDesc: MutableState<Int?>,
    selectedCategory: MutableState<String>
) {
    val colorGrey = colorResource(id = R.color.grey)
    val colorGreen = colorResource(id = R.color.green)
    val scrollStateText = rememberScrollState()


    val db = Firebase.firestore
    var selectedCarDescription by remember { mutableStateOf<CarDataModel?>(null) }

    // добавил ид тачки, нужно добавить категорию

    LaunchedEffect(Unit) {
        db.collection("cars")
            .document(selectedCategory.value)
            .collection("cars")
            .document(selectedCarForDesc.value.toString())
            .get()
            .addOnSuccessListener { result ->
                selectedCarDescription = result.toObject(CarDataModel::class.java)
            }
    }

    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            selectedCarDescription?.let {
                ImagePager(
                    onDecode,
                    it.carIcon1,
                    it.carIcon2,
                    it.carIcon3
                )
            }
        }
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
                    text = "${selectedCarDescription?.coast.toString()}+$"
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
                        text = selectedCarDescription?.consumption.toString()
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
                        text = "${selectedCarDescription?.mileage.toString()}+тыс. км"
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