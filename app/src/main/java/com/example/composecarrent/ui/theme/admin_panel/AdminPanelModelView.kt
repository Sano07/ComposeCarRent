package com.example.composecarrent.ui.theme.admin_panel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AdminPanelModelView : ViewModel() {

    var photoUri1  by mutableStateOf<Uri?>(null)

    var photoUri2  by mutableStateOf<Uri?>(null)

    var photoUri3  by mutableStateOf<Uri?>(null)

    fun setPhoto1(uri : Uri?) {
        photoUri1 = uri
    }
    fun setPhoto2(uri : Uri?) {
        photoUri2 = uri
    }
    fun setPhoto3(uri : Uri?) {
        photoUri3 = uri
    }
}