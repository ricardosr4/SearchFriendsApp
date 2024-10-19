package com.example.searchfriendsapp.ui.activity.preLogin.presenter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.searchfriendsapp.R
import com.example.searchfriendsapp.databinding.ActivityPreLoginBinding
import com.example.searchfriendsapp.ui.activity.homeContainer.HomeContainerActivity
import com.example.searchfriendsapp.ui.activity.login.presenter.LoginActivity
import com.example.searchfriendsapp.ui.activity.preLogin.loginToGoogle
import com.example.searchfriendsapp.ui.activity.register.presenter.RegisterActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class PreLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreLoginBinding
    private lateinit var myAuth: FirebaseAuth
    private val Google_SIGN_IN = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myAuth = FirebaseAuth.getInstance()

        navigation()
        sendEmailSupport()
        buttonLogin()


    }

    private fun buttonLogin() {
        binding.cvGoogle.setOnClickListener {
            val googleConfig = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(this, googleConfig)
            val signIntent = googleClient.signInIntent
            startActivityForResult(signIntent, Google_SIGN_IN)

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Google_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
               val account = task.getResult(ApiException::class.java)!!
                Log.d("Tag", "Firebasegoogleid $account.id" )
                firebaseAuthWithGoogle(account.idToken!!)
            }catch (e:ApiException){
                Log.w("Tag", "google sign in failed $e")

            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credentials = GoogleAuthProvider.getCredential(idToken, null)
        myAuth.signInWithCredential(credentials)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = myAuth.currentUser
                    val userEmail = user?.email.toString()
                    loginToGoogle(userEmail)
                } else {
                    Toast.makeText(this, "No se pudo loguear", Toast.LENGTH_SHORT).show()
                }
            }
    }





    private fun navigation() {
        binding.cvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.cvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.cvInvited.setOnClickListener {
            val intent = Intent(this, HomeContainerActivity::class.java)
            startActivity(intent)
        }
    }

    private fun sendEmailSupport() {
        binding.tvSupport.setOnClickListener {
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "rla.support@gmail.com", null))
            startActivity(Intent.createChooser(emailIntent, "enviar email..."))
        }
    }
}