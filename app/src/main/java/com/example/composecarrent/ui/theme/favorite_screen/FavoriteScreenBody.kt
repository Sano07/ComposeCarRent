package com.example.composecarrent.ui.theme.favorite_screen

import android.graphics.Bitmap
import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.CreationExtras
import coil.compose.AsyncImage
import com.example.composecarrent.R
import com.example.composecarrent.ui.theme.data.CarDataModel
import com.example.composecarrent.ui.theme.loaders.DescLoader
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.tasks.await

@Composable
fun FavoriteScreenBody(
    modifier: Modifier = Modifier,
    onFavCarChange: (Int) -> Unit,
    onDecode: (String) -> Bitmap,
    showMainLoader: MutableState<Boolean>
) {

    var favoriteCarList by remember { mutableStateOf<List<CarDataModel>>(emptyList()) }

    val db = Firebase.firestore
    val uid = Firebase.auth.currentUser!!.uid



    LaunchedEffect(Unit) {
        try {
            val result = db.collection("users")
                .document(uid)
                .collection("favCars")
                .get()
                .await()
            favoriteCarList = result.toObjects(CarDataModel::class.java)
        } catch (_: Exception) {
        } finally {
            showMainLoader.value = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (showMainLoader.value) {
            DescLoader(
                modifier = Modifier
                    .fillMaxSize()
                    .size(150.dp)
            )
        } else {
            if (favoriteCarList.isNotEmpty()) {
                LazyColumn(
                    modifier = modifier
                ) {
                    itemsIndexed(favoriteCarList, key = { _, item -> item.id }) { _, item ->
                        FavCarCards(
                            onDelete = { carId ->
                                db.collection("users")
                                    .document(uid)
                                    .collection("favCars")
                                    .document(carId.toString())
                                    .delete()
                                favoriteCarList = favoriteCarList.filter { it.id != carId }
                            },
                            list = item,
                            onFavCarChange,
                            onDecode
                        )
                    }
                }
            } else {
                EmptyFavCarScreen()
            }
        }
    }
}


@Composable
fun FavCarCards(
    onDelete: (Int) -> Unit,
    list: CarDataModel,
    onFavCarChange: (Int) -> Unit,
    onDecode: (String) -> Bitmap
) {

    val colorGreen = colorResource(id = R.color.green)
    val colorGrey = colorResource(id = R.color.grey)

    var isPressedRent by remember { mutableStateOf(false) }
    val scaleRent by animateFloatAsState(
        if (isPressedRent) 0.9f else 1f,
    )
    var isPressedDelete by remember { mutableStateOf(false) }
    val scaleDelete by animateFloatAsState(
        if (isPressedDelete) 0.9f else 1f,
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = onDecode(list.carIcon1),
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
                            contentDescription = "расход"
                        )
                        Text(
                            color = colorGrey,
                            text = "${list.consumption}л."
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
                Box(
                    modifier = Modifier
                        .graphicsLayer(
                            scaleX = scaleRent,
                            scaleY = scaleRent
                        )
                        .pointerInteropFilter {
                            when (it.action) {
                                MotionEvent.ACTION_DOWN -> isPressedRent = true
                                MotionEvent.ACTION_UP -> {
                                    isPressedRent = false
                                }

                                MotionEvent.ACTION_CANCEL -> isPressedRent = false
                            }
                            false
                        }
                ) {
                    Button(
                        onClick = {
                            // какая то логика для заказа машины
                            isPressedRent = false
                        },
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .width(120.dp),
                        colors = ButtonDefaults.buttonColors(Color.White),
                        border = BorderStroke(2.dp, Color.Black)
                    ) {
                        Text(text = "Rent", color = Color.Black)
                    }
                }
                Box(
                    modifier = Modifier
                        .graphicsLayer(
                            scaleX = scaleDelete,
                            scaleY = scaleDelete
                        )
                        .pointerInteropFilter {
                            when (it.action) {
                                MotionEvent.ACTION_DOWN -> isPressedDelete = true
                                MotionEvent.ACTION_UP -> {
                                    isPressedDelete = false
                                }

                                MotionEvent.ACTION_CANCEL -> isPressedDelete = false
                            }
                            false
                        }
                ) {
                    Button(
                        onClick = {
                            onDelete(list.id)
                            onFavCarChange(list.id)
                            isPressedDelete = false
                        },
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .width(120.dp),
                        colors = ButtonDefaults.buttonColors(Color.White),
                        border = BorderStroke(2.dp, Color.Black)
                    ) {
                        Text(text = "Delete", color = Color.Black)
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyFavCarScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(130.dp),
                painter = painterResource(R.drawable.im_empty_screen),
                contentDescription = ""
            )
            Text(
                fontSize = 20.sp,
                text = "List is empty : ("
            )
        }
    }
}