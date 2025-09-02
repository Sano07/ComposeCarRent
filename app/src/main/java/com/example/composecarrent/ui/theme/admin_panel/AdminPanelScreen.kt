package com.example.composecarrent.ui.theme.admin_panel

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.composecarrent.ui.theme.login_screen.AuthLogicViewModel

@Preview(showBackground = true)
@Composable
fun AdminPanelScreen(viewModel: AdminPanelModelView = viewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
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
                    value = "",
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = {},
                    label = { Text("Mark") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(20.dp))
                TextField(
                    value = "",
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = {},
                    label = { Text("Model") },
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
                    value = "",
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = {},
                    label = { Text("Coast") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(20.dp))
                TextField(
                    value = "",
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = {},
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
                    value = "",
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = {},
                    label = { Text("Mileage") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(20.dp))
                TextField(
                    value = "",
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = {},
                    label = { Text("Fuel") },
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
                    value = "",
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = {},
                    label = { Text("Transmission") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(20.dp))
                TextField(
                    value = "",
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = {},
                    label = { Text("Location") },
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
                    value = "",
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = {},
                    label = { Text("Car ID") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(20.dp))
                TextField(
                    value = "",
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = {},
                    label = { Text("Category") },
                    singleLine = true
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                shape = RoundedCornerShape(10.dp),
                onValueChange = {},
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
                ImportImage(viewModel.photoUri1, onImageSet = {viewModel.setPhoto1(it)})
                ImportImage(viewModel.photoUri2, onImageSet = {viewModel.setPhoto2(it)})
                ImportImage(viewModel.photoUri3, onImageSet = {viewModel.setPhoto3(it)})
            }
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(15.dp)
                        .height(60.dp),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color.White),
                    border = ButtonDefaults.outlinedButtonBorder(enabled = true),
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
        modifier = Modifier.size(110.dp),
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
