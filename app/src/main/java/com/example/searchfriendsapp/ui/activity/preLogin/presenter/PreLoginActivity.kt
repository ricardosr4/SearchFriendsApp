package com.example.searchfriendsapp.ui.activity.preLogin.presenter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.searchfriendsapp.databinding.ActivityPreLoginBinding
import com.example.searchfriendsapp.ui.activity.homeContainer.HomeContainerActivity
import com.example.searchfriendsapp.ui.activity.login.presenter.LoginActivity
import com.example.searchfriendsapp.ui.activity.register.presenter.RegisterActivity

class PreLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigation()
        sendEmailSupport()
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