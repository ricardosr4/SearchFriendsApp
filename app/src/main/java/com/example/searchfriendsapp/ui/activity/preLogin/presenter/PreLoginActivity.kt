package com.example.searchfriendsapp.ui.activity.preLogin.presenter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.searchfriendsapp.R
import com.example.searchfriendsapp.databinding.ActivityPreLoginBinding
import com.example.searchfriendsapp.ui.activity.homeContainer.HomeContainerActivity
import com.example.searchfriendsapp.ui.activity.login.presenter.LoginActivity
import com.example.searchfriendsapp.ui.activity.preLogin.viewModel.PreLoginViewModel
import com.example.searchfriendsapp.ui.activity.register.presenter.RegisterActivity
import com.example.searchfriendsapp.util.GoogleAuthState
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class PreLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreLoginBinding
    private val googleAuthViewModel by viewModels<PreLoginViewModel>()

  private val Google_SIGN_IN = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()
        setupGoogleLogin()
        setupNavigation()
    }

    private fun observeViewModel() {
        googleAuthViewModel.googleAuthState.observe(this) { state ->
            when (state) {
                is GoogleAuthState.Loading -> {
                    Toast.makeText(this, "Cargando...", Toast.LENGTH_SHORT).show()
                }

                is GoogleAuthState.Success -> {
                    Toast.makeText(
                        this,
                        "Inicio de sesión exitoso: ${state.email}",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this, HomeContainerActivity::class.java))
                    finish()
                }

                is GoogleAuthState.Error -> {
                    Toast.makeText(this, "Error: ${state.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupGoogleLogin() {
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

        if (requestCode == Google_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d("Tag", "Firebasegoogleid ${account.id}")
                googleAuthViewModel.firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w("Tag", "Google sign in failed", e)
            }
        }
    }

    private fun setupNavigation() {
        binding.cvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.cvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.cvInvited.setOnClickListener {
            startActivity(Intent(this, HomeContainerActivity::class.java))
        }
        binding.tvSupport.setOnClickListener {
            val emailIntent =
                Intent(
                    Intent.ACTION_SENDTO,
                    Uri.fromParts("mailto", "search.friends@gmail.com", null)
                )
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."))
        }
    }

}
