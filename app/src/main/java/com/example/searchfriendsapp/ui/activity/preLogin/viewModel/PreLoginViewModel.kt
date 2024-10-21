package com.example.searchfriendsapp.ui.activity.preLogin.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchfriendsapp.util.GoogleAuthState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

class PreLoginViewModel(private val auth: FirebaseAuth = FirebaseAuth.getInstance()) : ViewModel() {

    private val _googleAuthState = MutableLiveData<GoogleAuthState>()
    val googleAuthState: LiveData<GoogleAuthState> = _googleAuthState

    fun firebaseAuthWithGoogle(idToken: String) {
        viewModelScope.launch {
            _googleAuthState.value = GoogleAuthState.Loading

            val credential = GoogleAuthProvider.getCredential(idToken, null)
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        _googleAuthState.value = GoogleAuthState.Success(user?.email ?: "")
                    } else {
                        _googleAuthState.value = GoogleAuthState.Error(task.exception?.message ?: "Error desconocido")
                    }
                }
        }
    }
}
