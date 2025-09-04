package com.example.composecarrent.ui.theme.settings_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth


class SettingsScreenViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    var logOutStatus by mutableStateOf<String?>(null)
        private set

    fun logOut() {
        logOutStatus = "success"
        auth.signOut()
    }
}