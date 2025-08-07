package com.example.composecarrent.ui.theme.login_screen

import android.widget.Toast
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class AuthLogicViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    var registrationStatus by mutableStateOf<String?>(null)
        private set

    var signInStatus by mutableStateOf<String?>(null)
        private set

    var logOutStatus by mutableStateOf<String?>(null)
        private set

    var deleteAccountStatus by mutableStateOf<String?>(null)
        private set

    fun register(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            registrationStatus = "Cannot be empty"
            return
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                registrationStatus = if (task.isSuccessful) {
                    "success"
                } else {
                    task.exception?.message ?: "Unknown error"
                }
            }
    }

    fun signIn(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            signInStatus = "Cannot be empty"
            return
        }
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                signInStatus = if (task.isSuccessful) {
                    "success"
                } else {
                    task.exception?.message ?: "Unknown error"
                }
            }
    }

    fun logOut() {
        logOutStatus = "success"
        auth.signOut()
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