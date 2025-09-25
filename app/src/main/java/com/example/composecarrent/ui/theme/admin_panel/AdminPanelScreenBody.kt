package com.example.composecarrent.ui.theme.admin_panel

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.composecarrent.R
import com.example.composecarrent.ui.theme.data.CarDataModel

@Composable
fun AdminPanelScreenBody(
    modifier: Modifier = Modifier,
    viewModel: AdminPanelModelView = viewModel(),
    onStepBack: () -> Unit
) {
    val context =
        LocalContext.current
    val categoryList = listOf("Sedans", "Crossovers", "Full-size Sedans", "Coupes and Convertibles", "Electric Vehicles", "Moto")
    val fuelList = listOf("Diesel", "Gasoline", "Electricity" )
    val gearList = listOf("Automatic", "Mechanical", "Robotic", "Variator" )

    val addCarStatus = viewModel.addCarStatus

    val mark = viewModel.mark
    val modelYear = viewModel.modelYear
    val coast = viewModel.coast
    val consumption = viewModel.consumption
    val mileage = viewModel.mileage
    val fuel = viewModel.fuel
    val transmission = viewModel.transmission
    val location = viewModel.location
    val carId = viewModel.carId
    val category = viewModel.category
    val description = viewModel.description

    val carIcon1 = "carIcon"
    val carIcon2 = "carIcon"
    val carIcon3 = "carIcon"

    val newCar = CarDataModel(
        carId.toIntOrNull() ?: 0,
        category,
        carIcon1,
        carIcon2,
        carIcon3,
        mark,
        modelYear,
        coast,
        mileage,
        consumption,
        transmission,
        fuel,
        location,
        description
    )

    LaunchedEffect(addCarStatus) {
        if (addCarStatus != null && addCarStatus != "success") {
            Toast.makeText(
                context,
                "$addCarStatus",
                Toast.LENGTH_SHORT
            ).show()
        } else if (addCarStatus == "success") {
            Toast.makeText(
                context,
                "Save car was successful",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Enter parameters",
                color = Color.Black,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    value = mark,
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = { viewModel.mark = it },
                    label = { Text("Mark") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(20.dp))
                TextField(
                    value = modelYear,
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = { viewModel.modelYear = it },
                    label = { Text("Model and Year") },
                    singleLine = true
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    value = coast,
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = { viewModel.coast = it },
                    label = { Text("Coast") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(20.dp))
                TextField(
                    value = consumption,
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = { viewModel.consumption = it },
                    label = { Text("Consumption") },
                    singleLine = true
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    value = mileage,
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = { viewModel.mileage = it },
                    label = { Text("Mileage") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(20.dp))
                viewModel.DropDownMenu(modifier = Modifier.height(50.dp), "Fuel", fuelList) { selectedFuel ->
                    viewModel.fuel = selectedFuel
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    value = location,
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = { viewModel.location = it },
                    label = { Text("Location") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(20.dp))
                viewModel.DropDownMenu(modifier = Modifier.height(50.dp), "Transmission", gearList) { selectedGear ->
                    viewModel.transmission = selectedGear
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    value = carId,
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = { viewModel.carId = it },
                    label = { Text("Car ID") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(20.dp))
                    viewModel.DropDownMenu(modifier = Modifier.height(50.dp), "Category", categoryList) { selectedCategory ->
                        viewModel.category = selectedCategory
                    }
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = description,
                shape = RoundedCornerShape(10.dp),
                onValueChange = { viewModel.description = it },
                label = { Text("Description") },
                maxLines = 5
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Import images",
                color = Color.Black,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ImportImage(viewModel.photoUri1, onImageSet = { viewModel.setPhoto1(it) })
                ImportImage(viewModel.photoUri2, onImageSet = { viewModel.setPhoto2(it) })
                ImportImage(viewModel.photoUri3, onImageSet = { viewModel.setPhoto3(it) })
            }
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(15.dp)
                            .height(60.dp),
                        onClick = {
                            onStepBack()
                        },
                        colors = ButtonDefaults.buttonColors(Color.White),
                        border = BorderStroke(2.dp, Color.Black)
                    ) {
                        Text(
                            text = "Back",
                            color = Color.Black,
                            fontSize = 18.sp
                        )
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                            .height(60.dp),
                        onClick = {
                            viewModel.addNewCar(newCar, category, carId)
                        },
                        colors = ButtonDefaults.buttonColors(Color.White),
                        border = BorderStroke(2.dp, Color.Black)
                    ) {
                        Text(
                            text = "Save car",
                            color = Color.Black,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ImportImage(imageUri: Uri? = null, onImageSet: (Uri) -> Unit) {
    rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            onImageSet(uri)
        }
    }

    Button(
        onClick = { },
        modifier = Modifier.size(140.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(Color.LightGray)
    ) {
        if (imageUri != null) {
            Image(
                painter = rememberAsyncImagePainter(imageUri),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Icon(
                Icons.Default.AccountBox, contentDescription = ""
            )
        }

    }
}
