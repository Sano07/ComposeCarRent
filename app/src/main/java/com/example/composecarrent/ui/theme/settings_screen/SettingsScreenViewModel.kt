package com.example.composecarrent.ui.theme.settings_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth


class SettingsScreenViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    var logOutStatus by mutableStateOf<String?>(null)
        private set

    var deleteAccountStatus by mutableStateOf<String?>(null)
        private set

    fun logOut() {
        auth.signOut()
        logOutStatus = "success"
    }

    fun deleteAccount(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            return
        }
        val credentials = EmailAuthProvider.getCredential(email, password)
        auth.currentUser?.reauthenticate(credentials)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                auth.currentUser?.delete()
                deleteAccountStatus = "success"
            } else {
                task.exception?.message ?: "Unknown error"
            }
        }

    }
}