package com.example.composecarrent.ui.theme.admin_panel

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.net.Uri
import android.util.Base64
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.example.composecarrent.ui.theme.data.CarDataModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AdminPanelModelView : ViewModel() {

    private val db = Firebase.firestore

    var mark by mutableStateOf("")
    var modelYear by mutableStateOf("")
    var coast by mutableStateOf("")
    var consumption by mutableStateOf("")
    var mileage by mutableStateOf("")
    var fuel by mutableStateOf("")
    var transmission by mutableStateOf("")
    var location by mutableStateOf("")
    var carId by mutableStateOf("")
    var category by mutableStateOf("")
    var description by mutableStateOf("")

    var photoUri1 by mutableStateOf<Uri?>(null)

    var photoUri2 by mutableStateOf<Uri?>(null)

    var photoUri3 by mutableStateOf<Uri?>(null)

    var addCarStatus by mutableStateOf<String?>(null)
        private set

    fun setPhoto1(uri: Uri?) {
        photoUri1 = uri
    }

    fun setPhoto2(uri: Uri?) {
        photoUri2 = uri
    }

    fun setPhoto3(uri: Uri?) {
        photoUri3 = uri
    }

    // функция добавления новой машины, проверяется категория, если она есть - добавляется только машина
    // если категории нет, создается категория и добавляется машина
    // если есть машина, машина не записывается
    fun addNewCar(
        car: CarDataModel, category: String, carId: String
    ) {
        val carCategory = db.collection("cars").document(category)

        carCategory.get().addOnSuccessListener { cat ->
            if (!cat.exists()) {
                carCategory.set(mapOf("category" to category))
            }

            val carRoute = carCategory.collection("cars").document(carId)
            carRoute.get().addOnSuccessListener { carRouteCheck ->
                if (!carRouteCheck.exists()) {
                    carRoute.set(car)
                    addCarStatus = "success"
                } else {
                    addCarStatus = "Error. Check id or category"
                }
            }
        }
    }


    @SuppressLint("Recycle")
    fun imageToBase64(uri: Uri, contentResolver: ContentResolver) : String {
        val inputStream = contentResolver.openInputStream(uri)

        val bytes = inputStream?.readBytes()
        return bytes?.let {
            Base64.encodeToString(it, Base64.DEFAULT)
        } ?: ""
    }

    fun saveCarImages() {

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DropDownMenu(
        modifier: Modifier,
        label: String,
        options: List<String>,
        onSelected: (String) -> Unit
    ) {
        var openMenu by remember { mutableStateOf(false) }
        var selectedOption by remember { mutableStateOf("") }

        ExposedDropdownMenuBox(
            modifier = modifier,
            expanded = openMenu,
            onExpandedChange = { openMenu = !openMenu }
        ) {
            OutlinedTextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                label = { Text(label) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = openMenu)
                },
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled = true) // нужно для правильного позиционирования меню
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = openMenu,
                onDismissRequest = { openMenu = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            selectedOption = option
                            openMenu = false
                            onSelected(option)
                        }
                    )
                }
            }
        }
    }


}
