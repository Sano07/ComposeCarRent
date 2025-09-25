package com.example.composecarrent.ui.theme.admin_panel

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
                }
            }
        }
    }
}
