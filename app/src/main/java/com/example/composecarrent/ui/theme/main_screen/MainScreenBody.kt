package com.example.composecarrent.ui.theme.main_screen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.composecarrent.R
import com.example.composecarrent.ui.theme.data.CarDataModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import kotlinx.serialization.builtins.ByteArraySerializer
import kotlin.contracts.contract



@SuppressLint("UnrememberedMutableState")
@Composable
fun MainScreenBody(
    navController: NavController,
    selectedFavCars: List<Int>,
    onDecode: (String) -> Bitmap,
    isAdmin: MutableState<Boolean>,
    selectedCarForDesc: MutableState<Int?>,
    selectedCategory: MutableState<String>,
    clicked: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    favCars: Set<Int>,
    onFavCarChange: (Int) -> Unit
) {
    var selectedCarList by remember { mutableStateOf<List<CarDataModel>>(emptyList()) }

    val db = Firebase.firestore

    LaunchedEffect(selectedCategory.value) {
        db.collection("cars")
            .document(selectedCategory.value)
            .collection("cars")
            .get()
            .addOnSuccessListener { result ->
                selectedCarList = result.toObjects(CarDataModel::class.java)
            }
    }

    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(selectedCarList) { _, item ->
            val isFav = favCars.contains(item.id)
            CarCards(
                navController,
                onDecode,
                isAdmin,
                selectedCarForDesc,
                favCars,
                clicked,
                item,
                isFav,
                onFavCarChange = { onFavCarChange(item.id) })
        }
    }
}

@Composable
fun CarCards(
    navController: NavController,
    onDecode: (String) -> Bitmap,
    isAdmin: MutableState<Boolean>,
    selectedCarForDesc: MutableState<Int?>,
    favCars: Set<Int>,
    clicked: MutableState<Boolean>,
    item: CarDataModel,
    isFav: Boolean,
    onFavCarChange: (String) -> Unit
) {

    val colorGreen = colorResource(id = R.color.green)
    val colorGrey = colorResource(id = R.color.grey)
    val db = Firebase.firestore
    val uid = Firebase.auth.currentUser!!.uid

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        onClick = {
            navController.navigate("desc_screen")
            selectedCarForDesc.value = item.id
        }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {

            AsyncImage(
                model = onDecode(item.carIcon1),
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .aspectRatio(16 / 11f)
                    .clip(RoundedCornerShape(8.dp)),
                contentDescription = "машинка для примера",
                contentScale = ContentScale.Crop,
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
                        contentDescription = "расход"
                    )
                    Text(
                        color = colorGrey,
                        text = "${item.consumption}л."
                    )
                    Spacer(modifier = Modifier.width(5.dp))
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
                        painter = painterResource(id = R.drawable.ic_transmision),
                        contentDescription = "Коробка"
                    )
                    Text(
                        color = colorGrey,
                        text = item.transmission
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .size(15.dp),
                        painter = painterResource(id = R.drawable.ic_mileage),
                        contentDescription = "Пробег"
                    )
                    Text(
                        color = colorGrey,
                        text = "${item.mileage} т.км"
                    )
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),) {
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
                    onClick = {
                        onFavCarChange(item.id.toString())
                        val docId = db.collection("users")
                            .document(uid)
                            .collection("favCars")
                            .document(item.id.toString())
                        docId.get().addOnSuccessListener { doc ->
                            if (!doc.exists()) {
                                docId.set(item)
                            }
                        }
                        clicked.value = false
                    },
                    enabled = !favCars.contains(item.id),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 10.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(60.dp),
                        painter = painterResource(R.drawable.ic_favorite),
                        contentDescription = "Добавлено в избранное",
                        tint = if (isFav) Color.Red else Color.Black
                    )
                }
            }
        }
    }
}



