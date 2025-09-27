package com.example.searchfriendsapp.ui.activity.preLogin

import android.content.Context
import android.content.Intent
import com.example.searchfriendsapp.ui.activity.homeContainer.HomeContainerActivity
import com.example.searchfriendsapp.ui.activity.preLogin.presenter.PreLoginActivity
import com.google.firebase.auth.FirebaseAuth

fun Context.loginToGoogle(userEmail: String, ) {
    val intent = Intent(this, HomeContainerActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        putExtra("user_email", userEmail)

    }
    startActivity(intent)
}

fun Context.logout(){
    FirebaseAuth.getInstance().signOut()
    val intent = Intent(this, PreLoginActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    startActivity(intent)
}

