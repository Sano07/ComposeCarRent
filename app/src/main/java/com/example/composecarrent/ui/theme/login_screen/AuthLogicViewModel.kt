package com.example.composecarrent.ui.theme.login_screen

import android.widget.Toast
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class AuthLogicViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    var registrationStatus by mutableStateOf<String?>(null)
        private set

    fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                registrationStatus = if (task.isSuccessful) {
                    "success"
                } else {
                    task.exception?.message ?: "Unknown error"
                }
            }
    }
}